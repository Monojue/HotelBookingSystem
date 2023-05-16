package com.se.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.se.dto.BookingDto;
import com.se.entity.Customer;
import com.se.entity.Room;

@Mapper
public interface HotelBookingMapper {

	/**
	 * Retrieve the list of rooms
	 *
	 * @return the list of rooms
	 */
	public ArrayList<Room> getRoomList();

	/**
	 * Retrieve all room bookings
	 *
	 * @return the list of room bookings
	 */
	public ArrayList<BookingDto> getAllRoomList();

	/**
	 * Change the status of a room (occupied or available)
	 *
	 * @param status the new status of the room
	 * @param id     the ID of the room
	 */
	public void changeRoomStatus(@Param("status") boolean status, @Param("id") int id);

	/**
	 * Create a booking for a customer and a room
	 *
	 * @param customerId the ID of the customer
	 * @param roomId     the ID of the room
	 */
	public void createBooking(@Param("customerId") int customerId, @Param("roomId") int roomId);

	/**
	 * Cancel a booking for a customer and a room
	 *
	 * @param customerId the ID of the customer
	 * @param roomId     the ID of the room
	 */
	public void cancelBooking(@Param("customerId") int customerId, @Param("roomId") int roomId);

	/**
	 * Retrieve a specific room
	 *
	 * @return the specific room
	 */
	public Room getRoom();

	/**
	 * Insert a new user into the database
	 *
	 * @param user the user to insert
	 */
	public void insertUser(Customer user);

	/**
	 * Check if a customer with the given email and password exists in the database
	 *
	 * @param email    the email of the customer
	 * @param password the password of the customer
	 * @return the customer if found, null otherwise
	 */
	public Customer checkCustomerExist(@Param("email") String email, @Param("password") String password);

	/**
	 * Check if a customer with the given email already exists in the database
	 *
	 * @param email the email of the customer
	 * @return the customer if found, null otherwise
	 */
	public Customer checkCustomerAlreadySignup(@Param("email") String email);

}
