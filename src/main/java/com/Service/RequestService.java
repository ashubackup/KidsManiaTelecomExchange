package com.Service;

import java.time.LocalDateTime;

import org.apache.catalina.authenticator.SavedRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.SaveRequest;
import com.Model.BodyValue;
import com.Model.HeaderValue;
import com.Repository.SmsRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import net.bytebuddy.utility.nullability.AlwaysNull;

@Service
public class RequestService 
{

	@Autowired
	HeaderValue headerValue;
	
	@Autowired
	BodyValue bodyValue;
	
	@Autowired
	SmsRepository smsRepo;
	
	public String getRequest(String request)
	
	{
		String response = "";
		
		 JSONObject jsonObject = new JSONObject();
		 
		try
		{
			ObjectMapper xmlMapper = new XmlMapper();
			
	        // Convert the MessageRequest object to JSON
	        JsonNode jsonNode = xmlMapper.readTree(request);

	        SaveRequest savedRequest = new SaveRequest();
	        savedRequest.setDatetime(LocalDateTime.now());
	        savedRequest.setRequest(request);
	        smsRepo.save(savedRequest);
	        
	        System.out.println("The value of jsonNode"+jsonNode);
	        
//	        JsonNode envelope = jsonNode.get("Envelope");
	        JsonNode headerNode = jsonNode.path("Header");
	        JsonNode bodyNode = jsonNode.path("Body");
	        
	        //get Value From Header.
	        if (!headerNode.isMissingNode()) 
	        {
	           // Access properties within the "Header" element
	        	
	       
	          jsonObject = headerValue.getHeader(headerNode);
	           
	        } else {
	        	
	            System.out.println("Header element not found in the XML.");
	        }
	        
	        //get Vlaue from Body
	        if(!bodyNode.isMissingNode())
	        {
	        	
	        	response = bodyValue.getBodyValue(bodyNode,jsonObject);
	        }
	        else
	        {
	        	 System.out.println("Body element not found in the XML.");
	        }
	        
	       
	        
	        
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return response;
	}
}
