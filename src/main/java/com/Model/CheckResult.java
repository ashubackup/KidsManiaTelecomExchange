package com.Model;

import org.springframework.stereotype.Service;

@Service
public class CheckResult 
{

	public boolean processResult(String result)
	{
		boolean response = false;
		try
		{
			System.out.println("The calue of result"+result);
			if(result.equalsIgnoreCase("Success") || result.equalsIgnoreCase("You deactivate the service successfully."))
			{
				response=true;
			}
			else if(result.equalsIgnoreCase("1301"))
			{
				response=true;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("The calue of result"+response);

		return response;
	}
	
	public boolean checkResultCode(String resultCode)
	{
		
		boolean response =false;
		try
		{
			System.out.println("The value of resultCode"+resultCode);
			if(resultCode.equalsIgnoreCase("0"))
			{
				response = true;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("response"+response);
		return response;
	}
}
