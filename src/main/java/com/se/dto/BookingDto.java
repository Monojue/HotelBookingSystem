package com.se.dto;

import lombok.Data;

@Data
public class BookingDto {

	int id;
	String name;
	boolean status;
	String bookedCustomer;
	
}
