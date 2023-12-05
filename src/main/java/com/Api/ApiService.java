package com.Api;

import java.io.StringReader;
import java.time.LocalDateTime;
import org.w3c.dom.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Entity.Api;
import com.Entity.RequestResponseLogs;
import com.Entity.ServiceInfo;
import com.Entity.Tbl_sms;
import com.MD5.EncryptKey;
import com.Model.Response;
import com.Repository.ApiRepos;
import com.Repository.RequestResponseRepo;
import com.Repository.ServiceInfoRepo;
import com.Repository.TblSmsRepo;

import org.xml.sax.InputSource;

@Service
public class ApiService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ApiRepos apiRepos;

	@Autowired
	ServiceInfoRepo infoRepo;

	@Autowired
	RequestResponseRepo repo;
	
	@Autowired
	EncryptKey encryptKey;

	public void hitSubscription(String password, String timeStamp, String spId, String ani,String type)

	{
		System.out.println("The value of ani" + ani);
		String xmlRequest = "";
		try {
			long min = 1000000000000L; // 10^12
			long max = 9999999999999L; // (10^13 - 1)

			ServiceInfo info = infoRepo.findByServicename("MTN");

			// Generate a random 13-digit number
			long transactionId = min + (long) (Math.random() * (max - min + 1));
			Api api = apiRepos.findByStatusAndType("1", "sub");
			String hitApi = api.getApi();
			xmlRequest = api.getRequest();
			xmlRequest = xmlRequest.replace("<spid>", spId).replace("<password>", password).replace("<ani>", ani)
					.replace("<timestamp>", timeStamp).replace("<transId>", String.valueOf(transactionId))
					.replace("<serviceid>", info.getServiceid()).replace("<productid>", info.getProductid());

			String body = "";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.valueOf("text/xml; charset=utf-8"));

			HttpEntity<String> request = new HttpEntity<String>(xmlRequest, headers);
			System.out.println("The value of request is " + request);
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(hitApi, request, String.class);

			body = responseEntity.getBody();
			Integer status = responseEntity.getStatusCodeValue();

			System.out.println("The value of response" + body);

			// Create a DocumentBuilderFactory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			// Disable validation to work with SOAP namespaces
			factory.setValidating(false);
			factory.setNamespaceAware(true);

			// Create a DocumentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();

			// Parse the XML string
			Document document = builder.parse(new InputSource(new StringReader(body)));

			// Find the 'result' element
			NodeList nodeList = document.getElementsByTagName("result");
//			JAXBContext jaxbContext = JAXBContext.newInstance(Response.class);
//	        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//
//	        // Parse the XML string into a Java object
//	        Response response = (Response) unmarshaller.unmarshal(new StreamSource(new StringReader(body)));

			// Access the "result" value

			String result = "1000000";
			if (nodeList.getLength() > 0) {
				Element resultElement = (Element) nodeList.item(0);
				result = resultElement.getTextContent();

			}
			if (result.equalsIgnoreCase("0"))

			{
				RequestResponseLogs logs = new RequestResponseLogs();
				logs.setDatetime(LocalDateTime.now());
				logs.setRequest(request.toString());
				logs.setResponse(body.toString());
				logs.setType("sub");
				logs.setResult(result);
				logs.setMode(type);
				repo.save(logs);
				System.out.println("Data Saved in table request_Response_logs");
			} else {

				RequestResponseLogs logs = new RequestResponseLogs();
				logs.setDatetime(LocalDateTime.now());
				logs.setRequest(request.toString());
				logs.setResponse(body.toString());
				logs.setType("sub");
				logs.setMode(type);
				logs.setResult(result);
				repo.save(logs);
				System.out.println("Data Saved in table request_Response_logs");
			}

		} catch (Exception e) 
		
		{
			// TODO: handle exception
			e.printStackTrace();
			RequestResponseLogs logs = new RequestResponseLogs();
			logs.setDatetime(LocalDateTime.now());
			logs.setRequest(xmlRequest);
			logs.setResponse(e.getMessage());
			logs.setType("sub");
			logs.setMode(type);
			logs.setResult("Failed");
			repo.save(logs);
			System.out.println("Data Saved in table request_Response_logs");
		}

	}

	@Autowired
	TblSmsRepo smsRepo;

	public void hitForMessage(String spId, String password, String timeStamp, String ani, String type) {
		String xmlRequest = "";
		try {

			Tbl_sms sms = smsRepo.findByType(type);
			ServiceInfo info = infoRepo.findByServicename("MTN");

			Api api = apiRepos.findByStatusAndType("1", "sms");
			String hitApi = api.getApi();
			xmlRequest = api.getRequest();
			xmlRequest = xmlRequest.replace("<spid>", spId).replace("<password>", password).replace("<ani>", ani)
					.replace("<timestamp>", timeStamp).replace("<serviceid>", info.getServiceid())
					.replace("<shortCode>", "7227").replace("<message>", sms.getMessage()).replace("<OperatorA>", ani);
			String body = "";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.valueOf("text/xml; charset=utf-8"));

			HttpEntity<String> request = new HttpEntity<String>(xmlRequest, headers);
			System.out.println("Request is" + request);
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(hitApi, request, String.class);

			body = responseEntity.getBody().toString();
			System.out.println("The value of response" + body);
//			  
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			// Create a DocumentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();

			// Parse the XML from the input string
			Document document = builder.parse(new InputSource(new StringReader(body)));

			// Find the 'result' element
			NodeList nodeList = document.getElementsByTagName("ns2:result");

			String result = null;
			System.out.println("The value of nodeList");
			if (nodeList.getLength() > 0) {
				// Get the first 'result' element's text content
				String resultValue = nodeList.item(0).getTextContent();
				result = resultValue;
			} else {
				System.out.println("Result element not found in the XML.");
			}

			RequestResponseLogs logs = new RequestResponseLogs();
			logs.setDatetime(LocalDateTime.now());
			logs.setRequest(request.toString());
			logs.setResponse(body.toString());
			logs.setType("msg");
			logs.setResult(result);
			repo.save(logs);
			System.out.println("Data Saved in table request_Response_logs");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			RequestResponseLogs logs = new RequestResponseLogs();
			logs.setDatetime(LocalDateTime.now());
			logs.setRequest(xmlRequest);
			logs.setResponse(e.getMessage());
			logs.setType("msg");
			logs.setResult("Failed");
			repo.save(logs);
			System.out.println("Data Saved in table request_Response_logs");
		}
	}

	public void hitUnsubscription(String encryptedKey, String timeStamp, String spId, String ani) {
		String xmlRequest = "";
		try {
			long min = 1000000000000L; // 10^12
			long max = 9999999999999L; // (10^13 - 1)

			// Generate a random 13-digit number
			long transactionId = min + (long) (Math.random() * (max - min + 1));

			ServiceInfo info = infoRepo.findByServicename("MTN");

			Api api = apiRepos.findByStatusAndType("1", "unsub");
			String hitApi = api.getApi();
			xmlRequest = api.getRequest();
			xmlRequest = xmlRequest.replace("<spid>", spId).replace("<password>", encryptedKey).replace("<ani>", ani)
					.replace("<timestamp>", timeStamp).replace("<transId>", String.valueOf(transactionId))
					.replace("<serviceid>", info.getServiceid()).replace("<productid>", info.getProductid());

			String body = "";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.valueOf("text/xml; charset=utf-8"));

			HttpEntity<String> request = new HttpEntity<String>(xmlRequest, headers);
			System.out.println("The value of request is " + request);
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(hitApi, request, String.class);

			body = responseEntity.getBody();
			Integer status = responseEntity.getStatusCodeValue();

			System.out.println("The value of response" + body);

			// Create a DocumentBuilderFactory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			// Disable validation to work with SOAP namespaces
			factory.setValidating(false);
			factory.setNamespaceAware(true);

			// Create a DocumentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();

			// Parse the XML string
			Document document = builder.parse(new InputSource(new StringReader(body)));

			// Find the 'result' element
			NodeList nodeList = document.getElementsByTagName("result");
//				JAXBContext jaxbContext = JAXBContext.newInstance(Response.class);
//		        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			//
//		        // Parse the XML string into a Java object
//		        Response response = (Response) unmarshaller.unmarshal(new StreamSource(new StringReader(body)));

			// Access the "result" value

			String result = "1000000";
			if (nodeList.getLength() > 0) {
				Element resultElement = (Element) nodeList.item(0);
				result = resultElement.getTextContent();

			}
			if (result.equalsIgnoreCase("0"))

			{
				RequestResponseLogs logs = new RequestResponseLogs();
				logs.setDatetime(LocalDateTime.now());
				logs.setRequest(request.toString());
				logs.setResponse(body.toString());
				logs.setType("unsub");
				logs.setResult(result);
				repo.save(logs);
				System.out.println("Data Saved in table request_Response_logs");
			} else if (result.equalsIgnoreCase("1301")) 
			{
				String sp = "rainsms";
				String convertString = spId + spId +timeStamp;
				String encryptedKeyForSms = encryptKey.generateKey(convertString);
				hitForMessage(sp,encryptedKeyForSms, timeStamp, ani, "notunsub");
				RequestResponseLogs logs = new RequestResponseLogs();
				logs.setDatetime(LocalDateTime.now());
				logs.setRequest(request.toString());
				logs.setResponse(body.toString());
				logs.setType("unsub");
				logs.setResult(result);
				repo.save(logs);
				System.out.println("Data Saved in table request_Response_logs");
			}

			else {

				RequestResponseLogs logs = new RequestResponseLogs();
				logs.setDatetime(LocalDateTime.now());
				logs.setRequest(request.toString());
				logs.setResponse(body.toString());
				logs.setType("unsub");
				logs.setResult(result);
				repo.save(logs);
				System.out.println("Data Saved in table request_Response_logs");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			RequestResponseLogs logs = new RequestResponseLogs();
			logs.setDatetime(LocalDateTime.now());
			logs.setRequest(xmlRequest);
			logs.setResponse(e.getMessage());
			logs.setType("unsub");
			logs.setResult("Failed");
			repo.save(logs);
			System.out.println("Data Saved in table request_Response_logs");
		}

	}
}
