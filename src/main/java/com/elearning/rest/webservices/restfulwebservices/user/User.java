package com.elearning.rest.webservices.restfulwebservices.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

public class User extends RepresentationModel<User>{
	private Integer userId;
	
	@Size(min=2,message="Name should have at least 2 characters")
	private String userName;
	
	@Past
	private Date userBirthDate;

	public User(Integer userId, String userName, Date userBirthDate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userBirthDate = userBirthDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getUserBirthDate() {
		return userBirthDate;
	}

	public void setUserBirthDate(Date userBirthDate) {
		this.userBirthDate = userBirthDate;
	}
     
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userBirthDate=" + userBirthDate + "]";
	}

}
