package com.se.common;

import com.se.entity.Customer;
import com.se.form.SignUpForm;

public class Utils {

	/**
	 * Converts a SignUpForm object to a Customer entity.
	 * 
	 * @param form the SignUpForm object to convert
	 * @return the Customer entity with properties set from the SignUpForm
	 */
	public static Customer toEntity(SignUpForm form) {
		Customer user = new Customer();
		user.setId(form.getId());
		user.setName(form.getName());
		user.setEmail(form.getEmail());
		user.setPassword(form.getPassword());
		return user;
	}

	/**
	 * Converts a Customer entity to a SignUpForm object.
	 * 
	 * @param user the Customer entity to convert
	 * @return the SignUpForm object with properties set from the Customer
	 */
	public static SignUpForm toForm(Customer user) {
		SignUpForm form = new SignUpForm();
		form.setId(user.getId());
		form.setName(user.getName());
		form.setEmail(user.getEmail());
		form.setPassword(user.getPassword());
		form.setConfirmPassword(user.getPassword());
		return form;
	}

}
