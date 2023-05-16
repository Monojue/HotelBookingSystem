package com.se.entity;

import lombok.Data;

@Data
public class Booking {

	int id;
	String name;
	boolean status;
	String bookedCustomer;
	
}
