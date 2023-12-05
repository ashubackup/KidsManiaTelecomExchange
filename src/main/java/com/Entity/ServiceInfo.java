package com.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="serviceinfo")
public class ServiceInfo
{

	@Id
	private Integer id;
	private String serviceid;
	private String productid;
	private String servicename;
}
