package com.Controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Model.HeaderValue;
import com.Request.NotifySmsReception;
import com.Service.RequestService;
import com.Service.UssdService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@RestController
public class Request 
{

//	@Autowired
//    private XmlMapper xmlMapper;
//	
//	@Autowired
//	HeaderValue headerValue;
//	
	@Autowired
	RequestService requestService;
	
	@Autowired
	UssdService ussdService;
	
	
	@PostMapping(value = "/mosms") // , consumes = MediaType.APPLICATION_XML_VALUE
	public String getRequest(@RequestBody String  request)
	{
		 String jsonResult = null;
		 
//		 String jsonString = "{\"NotifySOAPHeader\":{\"spRevId\":\"\",\"spRevpassword\":\"vskvs\",\"spId\":\"xxxxxxxx\",\"timeStamp\":\"20221013153545\",\"OperatorID\":\"x\"}}";
		try
		{
			
			System.out.println("The value of request is"+request);
			jsonResult = requestService.getRequest(request);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return jsonResult;
	}
	
	@PostMapping(value = "/ussd" , consumes = MediaType.APPLICATION_XML_VALUE)
	public void getUssdRequest(@RequestBody String xmlRequest)
	{
		try
		{
			ussdService.getUSSDRequest(xmlRequest);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
