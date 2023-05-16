package com.se.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.se.entity.Booking;
import com.se.entity.Customer;
import com.se.entity.Room;

@Mapper
public interface HotelBookingMapper {

	// Retrieve the list of rooms
	public ArrayList<Room> getRoomList();

	// Retrieve all room bookings
	public ArrayList<Booking> getAllRoomList();

	// Change the status of a room (occupied or available)
	public void changeRoomStatus(@Param("status") boolean status, @Param("id") int id);

	// Create a booking for a customer and a room
	public void createBooking(@Param("customerId") int customerId, @Param("roomId") int roomId);

	// Cancel a booking for a customer and a room
	public void cancelBooking(@Param("customerId") int customerId, @Param("roomId") int roomId);

	// Retrieve a specific room
	public Room getRoom();

	// Insert a new user into the database
	public void insertUser(Customer user);

	// Check if a customer with the given email and password exists in the database
	public Customer checkCustomerExist(@Param("email") String email, @Param("password") String password);

}
