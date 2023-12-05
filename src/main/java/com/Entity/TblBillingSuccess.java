package com.Entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="tbl_billing_success")
public class TblBillingSuccess 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String ani;
	private String type;
	private LocalDateTime lastbilleddatetime;
	private LocalDateTime nextbilleddatetime;
	private LocalDateTime subdatetime;
	private String Chargedamount;
	private LocalDateTime datetime;
	private String mode;
}
