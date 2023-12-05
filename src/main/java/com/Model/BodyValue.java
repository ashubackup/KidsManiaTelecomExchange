package com.Model;

import java.awt.Paint;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.hibernate.internal.build.AllowSysOut;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Api.ApiService;
import com.MD5.EncryptKey;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class BodyValue {

	@Autowired
	ApiService apiService;

	private SecretKey secretKey;
	
	@Autowired
	EncryptKey encryptKey;

	public String getBodyValue(JsonNode bodyNode, JSONObject jsonObject) {
		String response = "<soapenv:Envelope xmlns:soapenv=”http://schemas.xmlsoap.org/soap/envelope/”\r\n"
				+ "xmlns:loc=”http://www.csapi.org/schema/parlayx/sms/notification/v2_2/local”>\r\n"
				+ " <soapenv:Header/>\r\n" + " <soapenv:Body>\r\n" + " <loc:notifySmsReceptionResponse/>\r\n"
				+ " </soapenv:Body>\r\n" + "</soapenv:Envelope>\r\n";
		try {
			JsonNode property1 = bodyNode.get("notifySmsReception");

			String correlator = property1.get("correlator").asText();
			System.out.println("Body: " + property1);

			// get Message Node From Body
			JsonNode messageNode = property1.get("message");
			System.out.println("MessageNode" + messageNode);

			// get Value from Message Node
			String message = messageNode.get("message").asText();
			System.out.println("Message" + message);
			String senderAddress = messageNode.get("senderAddress").asText();
			System.out.println("senderAddress" + senderAddress);
			String smsServiceActivationNumber = messageNode.get("smsServiceActivationNumber").asText();
			System.out.println("smsServiceActivationNumber" + smsServiceActivationNumber);
			String dateTime = messageNode.get("dateTime").asText();
			System.out.println("dateTime" + dateTime);
			System.out.println("The value of header" + jsonObject);
			System.out.println("");

			//mehtod for checking request

			String spId = "rainsub";
			String convertString = spId + spId + jsonObject.get("timeStamp").toString(); //jsonObject.get("spRevpassword").toString()
			
			String encryptedKey = encryptKey.generateKey(convertString);
//			String smsEncryptedKey = generateKey(smsSpId);
			System.out.println("The value of EncryptedKey" + encryptedKey);
			
			if (message.equalsIgnoreCase("KMD")) {
				
//				String smsSpId="rainsmsrainsms"+jsonObject.get("timeStamp").toString();
				{
					// String encryptedKey =encrypt(convertString);
					
					
					apiService.hitSubscription(encryptedKey, jsonObject.get("timeStamp").toString(), spId,
							senderAddress,"sms");
//					apiService.hitForMessage("rainsms", smsEncryptedKey,
//							jsonObject.get("timeStamp").toString(), senderAddress, message);
				}
			} else if (message.equalsIgnoreCase("STOP KMD")) 
			{
//				String spId="rainsub";
//				String convertString = spId + spId + jsonObject.get("timeStamp").toString(); //jsonObject.get("spRevpassword").toString()
//				String encryptedKey = generateKey(convertString);
				apiService.hitUnsubscription(encryptedKey,jsonObject.get("timeStamp").toString(), spId,senderAddress);
//				apiService.hitForMessage(spId, encryptedKey,
//						jsonObject.get("timeStamp").toString(), senderAddress, message);
			} else if(message.equalsIgnoreCase("Help KMD")) {
				String spIdForHelp="rainsms";
				String convertStringForHelp = spIdForHelp + spIdForHelp + jsonObject.get("timeStamp").toString(); //jsonObject.get("spRevpassword").toString()
				String encryptedKeyForHelp = encryptKey.generateKey(convertStringForHelp);
//				String smsEncryptedKey = generateKey(smsSpId);
				System.out.println("The value of EncryptedKeyFor HElp" + encryptedKeyForHelp);
				
				apiService.hitForMessage(spIdForHelp, encryptedKeyForHelp,jsonObject.get("timeStamp").toString(), senderAddress,"help");
			}

			else {
				String spIdForHelp="rainsms";
				String convertStringForHelp = spIdForHelp + spIdForHelp + jsonObject.get("timeStamp").toString(); //jsonObject.get("spRevpassword").toString()
				String encryptedKeyForHelp = encryptKey.generateKey(convertStringForHelp);
//				String smsEncryptedKey = generateKey(smsSpId);
				System.out.println("The value of EncryptedKeyFor HElp" + encryptedKeyForHelp);
				
//				apiService.hitForMessage(spIdForHelp, encryptedKeyForHelp,jsonObject.get("timeStamp").toString(), senderAddress, "wrongKeyword");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return response;
	}


//	public String generateKey(String text) {
//		 try {
//	            MessageDigest md = MessageDigest.getInstance("MD5");
//	            byte[] md5Hash = md.digest(text.getBytes());
//
//	            // Convert the byte array to a fixed-length 32-character hexadecimal string
//	            StringBuilder hexString = new StringBuilder(32);
//	            for (byte b : md5Hash) {
//	                String hex = String.format("%02x", b);
//	                hexString.append(hex);
//	            }
//
//	            return hexString.toString();
//	        } catch (NoSuchAlgorithmException e) {
//	            e.printStackTrace();
//	            return "Error: " + e.getMessage();
//	        }
//	}

}