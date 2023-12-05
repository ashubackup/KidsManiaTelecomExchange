package com.Request;

import javax.xml.bind.annotation.XmlElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotifySOAPHeader 
{

	  @XmlElement(name = "spRevId")
	 	private String spRevId;
	  
	  @XmlElement(name = "spRevpassword")
	    private String spRevpassword;
	  
	  @XmlElement(name = "spId")
	    private String spId;
	  
	  @XmlElement(name = "timeStamp")
	    private String timeStamp;
	  
	  @XmlElement(name = "OperatorID")
	    private String OperatorID;

}
