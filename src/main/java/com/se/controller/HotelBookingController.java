package com.se.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.se.common.Utils;
import com.se.entity.Customer;
import com.se.form.LoginForm;
import com.se.form.SelectedRoomList;
import com.se.form.SignUpForm;
import com.se.service.HotelBookingService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HotelBookingController {

	@Autowired
	HotelBookingService bookingService;

	// Home page
	@GetMapping("/")
	public String home(Model model, HttpSession session) {
		model.addAttribute("loginuser", null);
		model.addAttribute("selected", new SelectedRoomList());
		
		// Check if a customer is logged in and set the loginuser attribute accordingly
		if (session.getAttribute("customer") != null) {
			Customer loginUser = (Customer) session.getAttribute("customer");
			model.addAttribute("loginuser", loginUser);
		}
		
		model.addAttribute("booking", this.bookingService.getAllRoomList());
		return "roomlist";
	}

	// Login page
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("form", new LoginForm());
		return "login";
	}

	// Handle login form submission
	@PostMapping("/login")
	public String login(Model model, @Valid @ModelAttribute("form") LoginForm loginForm, BindingResult result,
			HttpSession session) {
		if (result.hasErrors()) {
			return "login";
		}
		
		// Check if the provided email and password match a customer in the system
		Customer customer = this.bookingService.checkCustomerExist(loginForm.getEmail(), loginForm.getPassword());
		if (customer == null) {
			model.addAttribute("error", "正しい認証情報を入力してください。");
			return "login";
		}
		
		session.setAttribute("customer", customer);
		return "redirect:/";
	}

	// Logout
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("customer");
		return "redirect:/";
	}

	// Signup page
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("form", new SignUpForm());
		return "signup";
	}

	// Handle signup form submission
	@PostMapping("/signup")
	public String signup(Model model, @Valid @ModelAttribute("form") SignUpForm userForm, BindingResult result) {
		if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {
			result.addError(new FieldError("form", "confirmPassword", "*パスワードが一致しません 。"));
		}
		if (result.hasErrors()) {
			return "signup";
		}
		
		// Insert the user into the system
		this.bookingService.insertUser(Utils.toEntity(userForm));
		return "redirect:/";
	}

	/**
	 * Register selected rooms
	 * 
	 * @param model
	 * @param roomList get user multiple selected room list in string formatted in 1,2,3
	 * @param session
	 * @param attributes
	 * @return
	 */
	@PostMapping("/register")
	public String registerRoom(Model model, @ModelAttribute("selected") SelectedRoomList roomList, HttpSession session,
			RedirectAttributes attributes) {
		if (session.getAttribute("customer") == null) {
			return "redirect:/login";
		}
		Customer customer = (Customer) session.getAttribute("customer");
		
		// Check in the customer for the selected rooms
		this.bookingService.checkIn(customer, roomList);
		
		attributes.addFlashAttribute("bookedRoomList", roomList.getSelectedRoomList());
		return "redirect:/";
	}

	/**
	 * Cancel selected rooms
	 * 
	 * @param model
	 * @param room - get user selected room to cancel booking
	 * @param session
	 * @return
	 */
	@PostMapping("/cancel")
	public String cancelRoom(Model model, @ModelAttribute("selected") SelectedRoomList room, HttpSession session) {
		if (session.getAttribute("customer") == null) {
			return "redirect:/login";
		}
		Customer customer = (Customer) session.getAttribute("customer");
		
		// Check out the customer from the selected rooms
		this.bookingService.checkout(customer, room);
		return "redirect:/";
	}

}
