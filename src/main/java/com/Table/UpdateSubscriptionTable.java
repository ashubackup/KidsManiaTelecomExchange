package com.Table;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Repository.TblSubRepo;

@Service
public class UpdateSubscriptionTable 
{
	@Autowired
	TblSubRepo repo;

	public void upateSubTble(JSONObject jsonObject , String ani)
	{
		try
		{
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
