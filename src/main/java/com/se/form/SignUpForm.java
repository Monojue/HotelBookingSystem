package com.se.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpForm {

	String id;
	@NotBlank(message = "*氏名を入力してください。")
	String name;
	@Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "*メールを正しく入力してください。")
	String email;
	@NotBlank(message = "*パスワードを入力してください。")
	@Size(min = 8, message = "パスワードは8文字以上で入力してください")
	String password;
	@NotBlank(message = "*パスワードを入力してください。")
	@Size(min = 8, message = "パスワードは8文字以上で入力してください")
	String confirmPassword;
}
