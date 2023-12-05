package com.Helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

@Service
public class ConvertIntoTime 
{

	public LocalDateTime dateTime(String input)
	{
		try
		{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	        LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
	        return dateTime;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return LocalDateTime.now();
		}
	}
}
