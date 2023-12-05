package com.Request;

import javax.xml.bind.annotation.XmlElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotifySmsBody 
{

	@XmlElement(name = "correlator")
	 private String correlator;
	
	@XmlElement(name = "message")
	 private Message message;

}
