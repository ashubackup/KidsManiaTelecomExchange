package com.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Service.HandleCallBackService;

@RestController
public class CallBackController 
{

	@Autowired
	HandleCallBackService backService;
	
	@PostMapping("/notify")
	public String getCallBack(@RequestBody String req)
	{
		String response = "failed";
		try
		{
			response = backService.handleRequest(req);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return response;
	}
}
