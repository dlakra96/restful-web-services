package com.elearning.rest.webservices.restfulwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about a specific user.")
@Entity
public class User extends RepresentationModel<User>{
	@Id
	@GeneratedValue
	private Integer userId;
	
	@Size(min=2,message="Name should have at least 2 characters")
	@ApiModelProperty(notes = "Name should have at least 2 characters")
	private String userName;
	
	@Past
	@ApiModelProperty(notes = "Birth date should be in the past")
	private Date userBirthDate;
	
	@OneToMany(mappedBy = "user")
	private List<Post> posts;

	public User(Integer userId, String userName, Date userBirthDate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userBirthDate = userBirthDate;
	}
	
	public User()
	{
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

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	
}
