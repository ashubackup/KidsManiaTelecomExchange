package com.Helper;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;


//@Data
//@AllArgsConstructor
//@NoArgsConstructor

@XmlRootElement(name = "item", namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local")
public class Item 
{

	private String key;
    private String value;

    @XmlElement
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @XmlElement
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}