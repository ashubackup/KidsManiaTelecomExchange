package com.Model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Tbl_Subscription;
import com.Repository.TblSubRepo;

@Service
public class CheckSubscriber 
{

	@Autowired
	TblSubRepo repo;
	
	public boolean checkSub(String ani)
	{
		boolean response = false;
		try
		{
			List<Tbl_Subscription> checkUser = repo.checkSub(ani);
			if(checkUser.size()!=0)
			{
				response=true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return response;
	}
	
	
//	public void checkUserNextBilledDate(String ani)
//	{
//		try
//		{
//			
//			
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
//	
}
