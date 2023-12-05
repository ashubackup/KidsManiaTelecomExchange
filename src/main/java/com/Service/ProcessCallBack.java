package com.Service;

import java.util.List;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Entity.CallBackEntity;
import com.Helper.XmlToJson;
import com.Helper.ConvertIntoTime;
import com.Helper.ExtractJson;
import com.Repository.CalBackRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Service
public class ProcessCallBack 
{
	
	@Autowired
	CalBackRepo backRepo;
	
	@Autowired
	ConvertIntoTime convertIntoTime;
	
	@Autowired
	XmlToJson xmlTojson;
	
	@Autowired
	ExtractJson extractJson;
	 
	 public void getDataFromCallBackTable()
	 {
		 try
		 {
			 ObjectMapper xmlMapper = new XmlMapper();
			 List<CallBackEntity> callBack = backRepo.findByStatus("0");
			 if(callBack.size()!=0)
			 {
				 for(CallBackEntity callBackEntity : callBack)
				 {
					 
					 String xmlStr = callBackEntity.getCallback().toString();
						JSONObject json = XML.toJSONObject(xmlStr);
						System.out.println("Tyhe valu of json"+json);
						extractJson.getMainJson(json,callBackEntity);
					 
				 }
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
	 }
}
