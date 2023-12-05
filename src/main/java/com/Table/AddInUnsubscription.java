package com.Table;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.TblUnsubscription;
import com.Entity.Tbl_Subscription;
import com.Repository.TblUnsubRepo;

@Service
public class AddInUnsubscription 
{

	@Autowired
	TblUnsubRepo unsubRepo;
	
	public boolean getUnSubData(Tbl_Subscription subscription)
	{
		boolean response = false;
		try
		{		
			TblUnsubscription tblUnsubscription = TblUnsubscription.builder()
													.ani(subscription.getAni())
													.productid(subscription.getProductid())
													.serviceid(subscription.getServiceid())
													.subdatetime(subscription.getSubdatetime())
													.lastbilleddatetime(subscription.getLastbilleddatetime())
													.nextbilleddatetime(subscription.getNextbilleddatetime())
													.channelid(subscription.getChannelid())
													.chargeamount(subscription.getChargeamount())
													.servicetype(subscription.getServicetype())
													.updatetype(subscription.getUpdatetype())
													.unsubdatetime(LocalDateTime.now())
													.build();
		
			unsubRepo.save(tblUnsubscription);
			response = true;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return response;
	}
	
}
