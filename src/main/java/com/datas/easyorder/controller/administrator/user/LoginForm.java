package com.datas.easyorder.controller.administrator.user;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class LoginForm {

	@Email
	@Size(min=2,max=50 )
	private String email;
	
	
	@Size(min=2,max=50)
	private String password;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
