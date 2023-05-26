package com.se.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.se.dto.BookingDto;
import com.se.dto.SelectedRoomListDto;
import com.se.entity.Customer;
import com.se.entity.Room;
import com.se.mapper.HotelBookingMapper;

@Service
@Transactional
public class HotelBookingService {

	@Autowired
	HotelBookingMapper bookingMapper;

	/**
	 * Get all room bookings
	 *
	 * @return the list of room bookings
	 */
	public ArrayList<BookingDto> getAllRoomList() {
		return this.bookingMapper.getAllRoomList();
	}

	/**
	 * Get a specific room
	 *
	 * @return the specific room
	 */
	public Room getRoom() {
		return this.bookingMapper.getRoom();
	}

	/**
	 * Insert a new user into the system
	 *
	 * @param user the user to insert
	 */
	public void insertUser(Customer user) {
		try {
			this.bookingMapper.insertUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
		}
	}

	/**
	 * Check if a customer with the given email and password exists in the system
	 *
	 * @param email    the email of the customer
	 * @param password the password of the customer
	 * @return the customer if found, null otherwise
	 */
	public Customer checkCustomerExist(String email, String password) {
		return this.bookingMapper.checkCustomerExist(email, password);
	}

	/**
	 * Check if a customer with the given email and password exists in the system
	 *
	 * @param email    the email of the customer
	 * @param password the password of the customer
	 * @return the customer if found, null otherwise
	 */
	public Customer checkCustomerAlreadySignup(String email) {
		return this.bookingMapper.checkCustomerAlreadySignup(email);
	}

	/**
	 * Check-in a customer for the selected rooms
	 *
	 * @param customer         the customer to check-in
	 * @param selectedRoomlist the selected room list
	 */
	public void checkIn(Customer customer, SelectedRoomListDto selectedRoomlist) {
		String[] roomList = selectedRoomlist.getSelectedRoomList().split(",");
		try {
			for (String roomId : roomList) {
				this.bookingMapper.createBooking(Integer.parseInt(customer.getId()), Integer.parseInt(roomId));
				this.bookingMapper.changeRoomStatus(true, Integer.parseInt(roomId));
			}
		} catch (Exception e) {
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
		}
	}

	/**
	 * Check out a customer from the selected room
	 *
	 * @param customer     the customer to check out
	 * @param selectedRoomlist the selected room
	 */
	public void checkout(Customer customer, SelectedRoomListDto selectedRoomlist) {
		String[] roomList = selectedRoomlist.getSelectedRoomList().split(",");
		try {
			for (String roomId : roomList) {
				this.bookingMapper.cancelBooking(Integer.parseInt(customer.getId()),
						Integer.parseInt(roomId));
				this.bookingMapper.changeRoomStatus(false, Integer.parseInt(roomId));
			}
		} catch (Exception e) {
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
		}
	}

}
