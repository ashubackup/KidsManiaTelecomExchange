package com.Helper;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Api.ApiService;
import com.Entity.CallBackEntity;
import com.Entity.Tbl_Subscription;
import com.MD5.EncryptKey;
import com.Model.CheckResult;
import com.Model.CheckSubscriber;
import com.Repository.CalBackRepo;
import com.Repository.TblSubRepo;
import com.Repository.TblUnsubRepo;
import com.Table.AddInUnsubscription;
import com.Table.SaveInTableBillingSuccess;
import com.Table.SaveInTableSubscription;
import com.Table.UpdateSubscriptionTable;

import lombok.val;

@Service
public class ExtractJson 
{

	@Autowired
	CalBackRepo backRepo;

	public void getMainJson(JSONObject jsonObject,CallBackEntity backEntity) {
		try {
			JSONObject extractEnvelope = new JSONObject(jsonObject.get("soapenv:Envelope").toString());
			System.out.println("The value of extractEnvelope JSON" + extractEnvelope);
			getBody(extractEnvelope,backEntity);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			backEntity.setStatus(e.getMessage());
			backRepo.save(backEntity);
		}
		
	}

	public void getBody(JSONObject jsonObject,CallBackEntity backEntity) {
		try {
			JSONObject bodyValue = new JSONObject(jsonObject.get("soapenv:Body").toString());
			System.out.println("The value of body Value" + bodyValue);
			getSyncOrderRelation(bodyValue,backEntity);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			backEntity.setStatus(e.getMessage());
			backRepo.save(backEntity);
		}
	}

	public void getSyncOrderRelation(JSONObject jsonObject,CallBackEntity backEntity) {
		try {
			JSONObject syncOrderRelation = new JSONObject(jsonObject.get("ns2:syncOrderRelation").toString());
			System.out.println("The value of syncOrderRelation" + syncOrderRelation);
			System.out.println("The value of " + syncOrderRelation.get("ns2:productID").toString());

			String ani = getUserId(syncOrderRelation,backEntity);
			getExtensionInfo(syncOrderRelation, ani,backEntity);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			backEntity.setStatus(e.getMessage());
			backRepo.save(backEntity);
		}
	}

	public String getUserId(JSONObject syncOrder,CallBackEntity backEntity) {
		String ani = "123";
		try {
			JSONObject userId = new JSONObject(syncOrder.get("ns2:userID").toString());
			System.out.println("The value of ani" + userId.get("ID").toString());
			ani = userId.get("ID").toString().trim();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			backEntity.setStatus(e.getMessage());
			backRepo.save(backEntity);
		}
		return ani;
	}

	public void getExtensionInfo(JSONObject jsonObject, String ani,CallBackEntity backEntity) {
		try {
			JSONObject extensionInfo = new JSONObject(jsonObject.get("ns2:extensionInfo").toString());
			System.out.println("The value of extensionInfo" + extensionInfo);
			getItem(extensionInfo.toString(), jsonObject, ani,backEntity);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			backEntity.setStatus(e.getMessage());
			backRepo.save(backEntity);
		}
	}

	@Autowired
	ApiService apiService;
	
	@Autowired
	ConvertIntoTime convertIntoTime;
	
	@Autowired
	TblSubRepo subRepo;
	
	@Autowired
	EncryptKey encryptKey;
	
	@Autowired
	SaveInTableBillingSuccess tableBillingSuccess;
	
	@Autowired
	CheckResult checkResult;
	
	@Autowired
	CheckSubscriber checkSubscriber;
	
	@Autowired
	SaveInTableSubscription tableSubscription;
	
	@Autowired
	UpdateSubscriptionTable updateSubTable;
	
	@Autowired
	AddInUnsubscription addInUnsubscription;
	
	public void getItem(String jsonStr, JSONObject syncOrder, String ani,CallBackEntity backEntity) {
		
			try {
				String spPasswordForMessage = "rainsmsrainsms"+syncOrder.get("ns2:updateTime").toString();
				String encryptedKey = encryptKey.generateKey(spPasswordForMessage);
				
				List<Tbl_Subscription> getSubDetail = subRepo.checkSub(ani);
				
//				if (getSubDetail!= null) 
//				{
////					getSubDetail.setNextbilleddatetime(nextbilleddatetime);
////					getSubDetail.setLastbilleddatetime(lastbilleddatetime);
////					subRepo.save(getSubDetail);
////					System.out.println("User" + ani + "NextBilledDateUpdate" + nextbilleddatetime);
//
//				} 
//				else {
//					Tbl_Subscription subscription = new Tbl_Subscription();
					JSONObject jsonObject = new JSONObject(jsonStr);
					JSONArray items = jsonObject.getJSONArray("item");
//					
					
//					subscription.setAni(ani);
//					subscription.setLastbilleddatetime(lastbilleddatetime);
//					subscription.setNextbilleddatetime(nextbilleddatetime);
//					subscription.setProductid(productId);
//				subscription.setServiceid(serviceId);
					
					boolean checkResultCode = false;
					boolean result = false;
					String resultValue="0";
					boolean checkUserAlreadyExistOrNot = false;
					String serviceType = "";
					String channelId = "0";
					String chargeAmount = "0";
					for (int i = 0; i < items.length(); i++) {
						JSONObject item = items.getJSONObject(i);
						String key = item.getString("key");
						Object value = item.get("value");
						
						
						if(key.equalsIgnoreCase("chargeAmount"))
						{
							chargeAmount = value.toString();
						}
						
						if(key.equalsIgnoreCase("resultCode"))
						{
							checkResultCode = checkResult.checkResultCode(value.toString());
						}
						
						if(key.equalsIgnoreCase("result"))
						{
							result = checkResult.processResult(value.toString());
							resultValue = value.toString();
						}
						
						if(key.equalsIgnoreCase("serviceType"))
						{
							serviceType = value.toString();
						}
						
						if(key.equalsIgnoreCase("channelID"))
						{
							channelId = value.toString();
						}
						System.out.println("Key: " + key + ", Value: " + value);
					}
						System.out.println("Type of callback"+syncOrder.get("ns2:updateDesc").toString() +"&result="+result+"REsultCOde"+checkResultCode);
						if(syncOrder.get("ns2:updateDesc").toString().equalsIgnoreCase("Addition") && result && checkResultCode)
						{
							System.out.println("Inside SubCallBack");
							checkUserAlreadyExistOrNot = checkSubscriber.checkSub(ani);
							
							if(checkUserAlreadyExistOrNot)
							{
								apiService.hitForMessage("rainsms", encryptedKey, syncOrder.get("ns2:updateTime").toString(), ani, "AlreadyExist");
								backEntity.setStatus("Done");
								backRepo.save(backEntity);
							}
							else
							{
								
								boolean subResponse = tableSubscription.saveUser(syncOrder,chargeAmount,ani,serviceType,channelId);
								System.out.println("Addedd in table sub"+subResponse);
								boolean billingResponse = tableBillingSuccess.saveInTableBillingSuccess(syncOrder,chargeAmount,ani,serviceType,channelId,"sub");
								System.out.println("Addedd in table Billing"+billingResponse);
								apiService.hitForMessage("rainsms", encryptedKey, syncOrder.get("ns2:updateTime").toString(), ani, "welcome");
								backEntity.setStatus("Done");
								backRepo.save(backEntity);
							}
						}
						else if(syncOrder.get("ns2:updateDesc").toString().equalsIgnoreCase("Modification") && result && checkResultCode)
						{
							
							System.out.println("Inside RenCallBack");
							checkUserAlreadyExistOrNot = checkSubscriber.checkSub(ani);
							if(checkUserAlreadyExistOrNot)
							{
								List<Tbl_Subscription> updateSub = subRepo.checkSub(ani);
								for(Tbl_Subscription updateLoop : updateSub)
								{
									updateLoop.setLastbilleddatetime(convertIntoTime.dateTime(syncOrder.get("ns2:effectiveTime").toString()));
									updateLoop.setNextbilleddatetime(convertIntoTime.dateTime(syncOrder.get("ns2:expiryTime").toString()));
									subRepo.save(updateLoop);
									System.out.println("TblSubscription Update"+updateLoop);
									
									backEntity.setStatus("Done");
									backRepo.save(backEntity);
								}
								boolean billingResponse = tableBillingSuccess.saveInTableBillingSuccess(syncOrder,chargeAmount,ani,serviceType,channelId,"REN");
							}
							else
							{
								boolean subResponse = tableSubscription.saveUser(syncOrder,chargeAmount,ani,serviceType,channelId);
								System.out.println("Addedd in table sub"+subResponse);
								boolean billingResponse = tableBillingSuccess.saveInTableBillingSuccess(syncOrder,chargeAmount,ani,serviceType,channelId,"sub");
								System.out.println("Addedd in table Billing"+billingResponse);
								backEntity.setStatus("Done");
								backRepo.save(backEntity);
							}
							
						}
						
						else if(syncOrder.get("ns2:updateDesc").toString().equalsIgnoreCase("Deletion") && result && checkResultCode)
						{
							
							System.out.println("Inside unSubCallBack");
							List<Tbl_Subscription> checkNumberInTblSub = subRepo.checkSub(ani);
							
							if(checkNumberInTblSub.size()!=0)
							{
								for(Tbl_Subscription checkSubForUnsub : checkNumberInTblSub)
								{
									addInUnsubscription.getUnSubData(checkSubForUnsub);
									System.out.println("The Number Add in table unsub");
									subRepo.delete(checkSubForUnsub);
									System.out.println("Subscriber Delete");
									backEntity.setStatus("Done");
									backRepo.save(backEntity);
								}
								
								apiService.hitForMessage("rainsms", encryptedKey, syncOrder.get("ns2:updateTime").toString(), ani, "unsub");
							
							}
							else
							{
								apiService.hitForMessage("rainsms", encryptedKey, syncOrder.get("ns2:updateTime").toString(), ani, "notunsub");
							}
						}
							
						
//					}
					
//					subRepo.save(subscription);
//					String spPassword = "rainsmsrainsms"+syncOrder.get("ns2:updateTime").toString();
//					String encryptedKey = encryptKey.generateKey(spPassword);
//					tableBillingSuccess.saveInTableBillingSuccess(subscription,"sub");
//					apiService.hitForMessage("rainsms", encryptedKey, syncOrder.get("ns2:updateTime").toString(), ani, "welcome");
					backEntity.setStatus("Done");
					backRepo.save(backEntity);
//				}
			} 
	catch (Exception e) 
			{
				e.printStackTrace();
				backEntity.setStatus(e.getMessage());
				backRepo.save(backEntity);
			}

	}

}
