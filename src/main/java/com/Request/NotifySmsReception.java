package com.Request;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement(name = "Envelope")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotifySmsReception 
{

	 private NotifySOAPHeader header;
	 private NotifySmsBody body;
}
