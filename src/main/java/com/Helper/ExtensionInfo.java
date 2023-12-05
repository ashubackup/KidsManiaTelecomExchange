package com.Helper;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement(name = "extensionInfo", namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local")
public class ExtensionInfo {
    private List<String> item;

//    @XmlElement
//    public List<Item> getItem() {
//        return item;
//    }
//
//    public void setItem(List<Item> item) {
//        this.item = item;
//    }
}