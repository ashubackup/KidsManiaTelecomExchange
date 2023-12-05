package com.Entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.aspectj.lang.annotation.control.CodeGenerationHint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="request_response_logs")
public class RequestResponseLogs 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(columnDefinition = "Text")
	private String request;
	@Column(columnDefinition = "Text")
	private String response;
	private LocalDateTime datetime;
	private String type;
	@Column(columnDefinition = "TEXT")
	private String result;
	
	private String mode;
}
