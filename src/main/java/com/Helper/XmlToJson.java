package com.Helper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class XmlToJson 
{
	
	@Autowired
	ConvertIntoTime convertIntoTime;

	public void extractValue(JsonNode jsonNode)
	{
		try
		{
			 JsonNode body = jsonNode.path("Body");
			 System.out.println("the value do envolpe"+body);
			 JsonNode syncOrderRelation = body.path("syncOrderRelation");
			 System.out.println("The value of syncOrder"+syncOrderRelation);
			 
			 JsonNode userID = syncOrderRelation.path("userID");
			 System.out.println("The value of userId"+userID);
			 JsonNode id = userID.path("id");
			 String spID = syncOrderRelation.path("spID").asText();
			 System.out.println("SpID"+spID);
			 String productID = syncOrderRelation.get("productID").asText();
			 System.out.println("the value of prouct"+productID);
			 String serviceID = syncOrderRelation.get("serviceID").asText();
			 String updateType = syncOrderRelation.get("updateType").asText();
			 String updateDesc = syncOrderRelation.get("updateDesc").asText();
			 System.out.println("the value of prouct"+updateDesc);

			 LocalDateTime 	updateTime =  convertIntoTime.dateTime((syncOrderRelation.path("updateTime").asText()));
			 LocalDateTime effectiveTime = convertIntoTime.dateTime((syncOrderRelation.path("effectiveTime").asText()));
			 LocalDateTime expiryTime = convertIntoTime.dateTime((syncOrderRelation.path("expiryTime").asText()));
			 System.out.println("updateTime"+updateTime);
			 System.out.println("effectiveTime"+effectiveTime);
			 System.out.println("expiryTime"+expiryTime);
			 JsonNode extensionInfo = syncOrderRelation.get("extensionInfo");
			 System.out.println("********");
			 JsonNode items = extensionInfo.get("item");
//			 String text = items.get("key").get("serviceType").textValue();
//			 System.out.println("Text"+text);
			 for(JsonNode item : items)
			 {
				 String key = item.get("key").get("serviceType").asText(); // Extract the "key" as a String
				    String value = item.get("value").asText(); // Extract the "value" as a String

				    // Use key and value as needed
				    System.out.println("Key: " + key);
				    System.out.println("Value: " + value);
				     
			 }
			 
			 
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
