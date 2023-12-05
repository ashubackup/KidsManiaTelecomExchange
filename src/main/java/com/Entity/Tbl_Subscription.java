package com.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;

import org.hibernate.internal.build.AllowSysOut;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor
@Builder
@Entity
@Table(name="tbl_subscription")
public class Tbl_Subscription
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String ani;
	private String productid;
	private String serviceid;
	private LocalDateTime subdatetime;
	private LocalDateTime lastbilleddatetime;
	private LocalDateTime nextbilleddatetime;
	private String chargeamount;
	private String servicetype;
	private String updatetype;
	private String channelid;
	private LocalDateTime unsubdatetime;
	private String mode;
}
