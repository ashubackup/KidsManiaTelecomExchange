package com.Model;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement(name = "subscribeProductResponse", namespace = "http://www.csapi.org/schema/parlayx/subscribe/manage/v1_0/local")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response 
{
	private String result;
}
