package com.Helper;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "userID")
public class UserID {
    private String ID;
    private int type;

//    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local")
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

//    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
}