package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@SpringBootApplication
@EnableScheduling
public class RainbooSmsFlowApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RainbooSmsFlowApplication.class, args);
	}
	
	 @Bean
	    public XmlMapper xmlMapper() {
	        XmlMapper xmlMapper = new XmlMapper();

	        // Configure XmlMapper if needed (e.g., for custom naming strategies, date formats, etc.)
	        // xmlMapper.configure(...);

	        return xmlMapper;

	 }
	 
	 @Bean
	 public RestTemplate restTemplate()
	 {
		 return new RestTemplate();
	 }
}
