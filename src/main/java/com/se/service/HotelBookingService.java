package com.se.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.se.entity.Booking;
import com.se.entity.Customer;
import com.se.entity.Room;
import com.se.form.SelectedRoomList;
import com.se.mapper.HotelBookingMapper;

@Service
@Transactional
public class HotelBookingService {

	@Autowired
	HotelBookingMapper bookingMapper;

	// Get the list of rooms
	public ArrayList<Room> getRoomList() {
		return this.bookingMapper.getRoomList();
	}

	// Get all room bookings
	public ArrayList<Booking> getAllRoomList() {
		return this.bookingMapper.getAllRoomList();
	}

	// Get a specific room
	public Room getRoom() {
		return this.bookingMapper.getRoom();
	}

	// Insert a new user into the system
	public void insertUser(Customer user) {
		try {
			this.bookingMapper.insertUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
		}
	}

	// Check if a customer with the given email and password exists in the system
	public Customer checkCustomerExist(String email, String password) {
		return this.bookingMapper.checkCustomerExist(email, password);
	}

	// Check-in a customer for the selected rooms
	public void checkIn(Customer customer, SelectedRoomList selectedRoomlist) {
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

	// Check out a customer from the selected room
	public void checkout(Customer customer, SelectedRoomList selectedRoom) {
		try {
			this.bookingMapper.cancelBooking(Integer.parseInt(customer.getId()),
					Integer.parseInt(selectedRoom.getSelectedRoomList()));
			this.bookingMapper.changeRoomStatus(false, Integer.parseInt(selectedRoom.getSelectedRoomList()));
		} catch (Exception e) {
			e.printStackTrace();
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
		}
	}

}
