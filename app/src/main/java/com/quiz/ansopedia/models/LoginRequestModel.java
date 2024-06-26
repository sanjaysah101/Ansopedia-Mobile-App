package com.quiz.ansopedia.models;

import com.google.gson.annotations.SerializedName;

public class LoginRequestModel{

	@SerializedName("name")
	private String name;

	@SerializedName("username")
	private String username;

	@SerializedName("password")
	private String password;

	@SerializedName("email")
	private String email;

	@SerializedName("otp")
	private String otp;

	@SerializedName("password_confirmation")
	private String password_confirmation;

	@SerializedName("message")
	private String message;

	@SerializedName("tc")
	private boolean tc;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getPassword_confirmation() {
		return password_confirmation;
	}

	public void setPassword_confirmation(String password_confirmation) {
		this.password_confirmation = password_confirmation;
	}

	public boolean getTc() {
		return tc;
	}

	public void setTc(boolean tc) {
		this.tc = tc;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}