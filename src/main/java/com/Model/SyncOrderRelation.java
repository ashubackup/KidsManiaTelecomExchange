package com.Model;


import javax.xml.bind.annotation.XmlRootElement;

import com.Helper.ExtensionInfo;
import com.Helper.UserID;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlElement;

@JsonIgnoreProperties
@XmlRootElement(name = "syncOrderRelation", namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local")
public class SyncOrderRelation {

    private UserID userID;
    private String spID;
    private String productID;
    private String serviceID;
    private int updateType;
    private String updateTime;
    private String updateDesc;
    private String effectiveTime;
    private String expiryTime;
    private ExtensionInfo extensionInfo;

    @XmlElement(name = "userID", namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local")
    public UserID getUserID() {
        return userID;
    }

    public void setUserID(UserID userID) {
        this.userID = userID;
    }

    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local")
    public String getSpID() {
        return spID;
    }

    public void setSpID(String spID) {
        this.spID = spID;
    }

    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local")
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local")
    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local")
    public int getUpdateType() {
        return updateType;
    }

    public void setUpdateType(int updateType) {
        this.updateType = updateType;
    }

    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local")
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local")
    public String getUpdateDesc() {
        return updateDesc;
    }

    public void setUpdateDesc(String updateDesc) {
        this.updateDesc = updateDesc;
    }

    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local")
    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local")
    public String getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(String expiryTime) {
        this.expiryTime = expiryTime;
    }

    @XmlElement(name = "extensionInfo", namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local")
    public ExtensionInfo getExtensionInfo() {
        return extensionInfo;
    }

    public void setExtensionInfo(ExtensionInfo extensionInfo) {
        this.extensionInfo = extensionInfo;
    }

	@Override
	public String toString() {
		return "SyncOrderRelation [userID=" + userID + ", spID=" + spID + ", productID=" + productID + ", serviceID="
				+ serviceID + ", updateType=" + updateType + ", updateTime=" + updateTime + ", updateDesc=" + updateDesc
				+ ", effectiveTime=" + effectiveTime + ", expiryTime=" + expiryTime + ", extensionInfo=" + extensionInfo
				+ "]";
	}
    
}
