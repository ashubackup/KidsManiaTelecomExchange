package com.Table;

import java.time.LocalDateTime;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Tbl_Subscription;
import com.Helper.ConvertIntoTime;
import com.Repository.TblSubRepo;

@Service
public class SaveInTableSubscription 
{
	
	@Autowired
	TblSubRepo repo;
	
	@Autowired
	ConvertIntoTime convertIntoTime;

	public boolean saveUser(JSONObject syncOrder , String amount,String ani,String serviceType,String channelId)
	{
		 
		boolean response = false;
		try
		{
			Tbl_Subscription subscription = Tbl_Subscription.builder()
											.ani(ani)
											.productid(syncOrder.get("ns2:productID").toString())
											.serviceid(syncOrder.get("ns2:serviceID").toString())
											.subdatetime(convertIntoTime.dateTime(syncOrder.get("ns2:updateTime").toString()))
											.lastbilleddatetime(convertIntoTime.dateTime(syncOrder.get("ns2:effectiveTime").toString()))
											.nextbilleddatetime(convertIntoTime.dateTime(syncOrder.get("ns2:expiryTime").toString()))
											.chargeamount(amount)
											.servicetype(serviceType)
											.updatetype(syncOrder.get("ns2:updateType").toString())
											.channelid(channelId)
											.build();
			
			System.out.println("TblSubscription"+subscription);
			repo.save(subscription);
			response=true;
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return response;
	}
}
