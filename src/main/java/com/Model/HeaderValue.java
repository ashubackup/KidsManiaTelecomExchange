package com.Model;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class HeaderValue 
{

	public JSONObject getHeader(JsonNode headerNode)
	{
		
		 JSONObject jsonObject = new JSONObject();
		try
		{
			 JsonNode header = headerNode.get("NotifySOAPHeader"); 
			 String spRevpassword = header.get("spRevpassword").asText();
			 String spRevId = header.get("spRevId").asText();
			 String spId = header.get("spId").asText();
			 String timeStamp  = header.get("timeStamp").asText();
			 String OperatorID = header.get("OperatorID").asText();
			 
			 System.out.println("spRevpassword"+spRevpassword);
			 System.out.println("spRevId"+spRevId);
			 System.out.println("spId"+spId);
			 System.out.println("timeStamp"+timeStamp);
			 System.out.println("OperatorID"+OperatorID);
			 
			 
			
			 jsonObject.put("spRevpassword", spRevpassword);
	         jsonObject.put("spRevId", spRevId);
	         jsonObject.put("spId", spId);
	         jsonObject.put("timeStamp", timeStamp);
	         jsonObject.put("OperatorID", OperatorID);
	         
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return jsonObject;
	}
	
}
