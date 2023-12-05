package com.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Api.ApiService;
import com.Entity.Tbl_Subscription;
import com.Entity.WapRequestEntity;
import com.MD5.EncryptKey;
import com.Repository.TblSubRepo;
import com.Repository.WapRequestRepo;

@Service
public class WapSubService 
{

	@Autowired
	EncryptKey encryptKey;
	
	@Autowired
	TblSubRepo subRepo;
	
	@Autowired
	ApiService api;
	
	@Autowired
	WapRequestRepo  requestRepo;

	
	public String handlesubRequest(String ani)
	{
		//send response according to user 
		//if status 1; not subscribe 
		String status ="1" ;
		try
		{
			Date now = new Date();
			
			 // Create a SimpleDateFormat with the desired pattern
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	        // Set the time zone to Nigeria (Africa/Lagos)
	        dateFormat.setTimeZone(TimeZone.getTimeZone("Africa/Lagos"));

	        WapRequestEntity entity = new WapRequestEntity();
	        entity.setAni(ani);
	        entity.setDatetime(LocalDateTime.now());
	        requestRepo.save(entity);
	        
	        
	        // Format the date to the specified pattern
	        String timestamp = dateFormat.format(now);

	        System.out.println("TimeStamp"+timestamp);
	        
	        List<Tbl_Subscription> getSubData = subRepo.checkSub(ani);
	        
	        boolean userCheck = false;
	        if(getSubData.size()!=0)
	        {
	        for(Tbl_Subscription subscription : getSubData)
	        {
	        	if(subscription.getNextbilleddatetime().toLocalDate().isAfter(LocalDate.now()))
	        	{
	        		System.out.print("already sub");
	        		userCheck=true;
	        		//success subscribe need to give permission
	        		status = "2";
	        	}
	        }
	        }
	        if(!userCheck)
	        {
	        	String type="Wap";
	        	System.out.println("hit sub api");
	        	String spId = "rainsub";
				String convertString = spId + spId + timestamp ; //jsonObject.get("spRevpassword").toString()
				String encryptedKey = encryptKey.generateKey(convertString);
				api.hitSubscription(encryptedKey, timestamp, spId, ani,type);
	        }	
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return status;
	}
	
}
