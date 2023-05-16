package com.se.entity;

import lombok.Data;

/**
 * Customer Table
 * 
 * @author ASUS
 *
 */
@Data
public class Customer {
	
	String id;
	String name;
	String email;
	String password;
}
