package com.Scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.Service.ProcessCallBack;

@Component
//@RestController
public class CallBackProcess 
{
	@Autowired
	ProcessCallBack processCallBack;

	@Scheduled(fixedDelay = 2000L) //stop on local but working on live 
	public void processData()
	{
		System.out.println("Scheduler working for processCallback KidsMania");
		processCallBack.getDataFromCallBackTable();
	}
}
