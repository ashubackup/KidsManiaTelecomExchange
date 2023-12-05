package com.Controller;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.Service.WapSubService;

@RestController
@CrossOrigin("*")
public class WapRequest 
{
	
	@Autowired
	WapSubService wapSubService;
	

	@PostMapping("/WapRequest")
	public ResponseEntity<String> getRequest(@RequestHeader Map<String,String> header,@RequestBody String ani)
	{
		try
		{
			System.out.println(":::::::Headers Kids Mania:::::");
			
			System.out.println("Header"+header);
			System.out.println("Ani"+ani);
//			JSONObject jsonObject = new JSONObject(ani);
//			System.out.println("jsonObject"+jsonObject);
			 String stringWithoutFirstZero = removeFirstZero(ani);
			
			System.out.println("RemoveZero"+stringWithoutFirstZero);
			String response = wapSubService.handlesubRequest(stringWithoutFirstZero);
			return ResponseEntity.ok(response);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
		}
	}
	
	public String removeFirstZero(String ani)
	{
		String response = "1234";
		try
		{
			 String stringWithoutFirstZero = ani.replaceFirst("0", "");
			 response=stringWithoutFirstZero;
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return response;
	}
}
