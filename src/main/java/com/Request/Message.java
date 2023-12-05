package com.Request;

import javax.xml.bind.annotation.XmlElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message 
{

	 @XmlElement(name = "message")
	 private String message;
	 
	 @XmlElement(name = "senderAddress")
	 private String senderAddress;
	 
	 @XmlElement(name = "smsServiceActivationNumber")
	 private String smsServiceActivationNumber;
	 
	 @XmlElement(name = "dateTime")
	 private String dateTime;
}
