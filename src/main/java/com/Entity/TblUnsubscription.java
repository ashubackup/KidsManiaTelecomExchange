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
@Builder
@Entity
@Table(name="tbl_unsubscription")
public class TblUnsubscription 
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
