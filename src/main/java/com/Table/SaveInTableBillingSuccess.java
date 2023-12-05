package com.Table;

import java.time.LocalDateTime;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.TblBillingSuccess;
import com.Entity.Tbl_Subscription;
import com.Helper.ConvertIntoTime;
import com.Repository.TblBillingSuccessRepo;

@Service
public class SaveInTableBillingSuccess 
{

	
	@Autowired
	TblBillingSuccessRepo billingSuccessRepo;
	
	@Autowired
	ConvertIntoTime convertIntoTime;
	
	
	public boolean saveInTableBillingSuccess(JSONObject syncOrder , String amount,String ani,String serviceType,String channelId,String type)
	{
		
		
		boolean response = false;
		try
		{
			
			
			
			TblBillingSuccess billingSuccess = TblBillingSuccess.builder()
												.ani(ani)
												.Chargedamount(amount)
												.datetime(LocalDateTime.now())
												.lastbilleddatetime(convertIntoTime.dateTime(syncOrder.get("ns2:effectiveTime").toString()))
												.nextbilleddatetime(convertIntoTime.dateTime(syncOrder.get("ns2:expiryTime").toString()))
												.subdatetime(convertIntoTime.dateTime(syncOrder.get("ns2:updateTime").toString()))
												.type(type)
												.build();
			
			billingSuccessRepo.save(billingSuccess);
			
			response=true;
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return response;
	}
}
