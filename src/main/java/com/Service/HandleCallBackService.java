package com.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.Entity.CallBackEntity;
import com.Repository.CalBackRepo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Service
public class HandleCallBackService 
{
	@Autowired
	CalBackRepo backRepo;

	public String handleRequest(String request)
	{
		
		ObjectMapper xmlMapper = new XmlMapper();
		String finalResp = "failed";
		String response = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\r\n"
				+ "xmlns:loc=\"http://www.csapi.org/schema/parlayx/data/sync/v1_0/local\">\r\n"
				+ "<soapenv:Header/>\r\n"
				+ "<soapenv:Body>\r\n"
				+ "<loc:syncOrderRelationResponse>\r\n"
				+ "<loc:result>0</loc:result>\r\n"
				+ "<loc:resultDescription>"+finalResp+"</loc:resultDescription>\r\n"
				+ "</loc:syncOrderRelationResponse>\r\n"
				+ "</soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";
		try
		{
			 JsonNode jsonNode = xmlMapper.readTree(request);
			 JsonNode body = jsonNode.path("Body");
			 JsonNode syncOrderRelation = body.path("syncOrderRelation");
			 String updateDesc = syncOrderRelation.path("updateDesc").asText();
			CallBackEntity backEntity = new CallBackEntity();
			backEntity.setCallback(request);
			backEntity.setDatetime(LocalDateTime.now());
			backEntity.setStatus("0");
			backEntity.setType(updateDesc);
			backRepo.save(backEntity);
			finalResp = "OK";
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return response;
	}
}
