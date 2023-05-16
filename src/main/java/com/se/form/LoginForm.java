package com.se.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginForm {

	@Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "*メールを正しく入力してください。")
	String email;
	@NotBlank(message = "*パスワードを入力してください。")
	String password;	
}
