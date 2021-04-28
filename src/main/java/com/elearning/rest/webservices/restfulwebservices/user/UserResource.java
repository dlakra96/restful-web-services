package com.elearning.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.hateoas.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return service.findAll(); 
	}
	
	@GetMapping("/users/{userId}")
	public User retrieveUserById(@PathVariable int userId)
	{
		User user = service.findOne(userId);
		if(user == null)
			throw new UserNotFoundException("id :- " + userId);
		Link usersLink = linkTo(methodOn(this.getClass()).retrieveAllUsers()).withRel("all-users");
		user.add(usersLink);
		return user;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user)
	{
		User savedUser = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getUserId()).toUri();
		return ResponseEntity.created(location).build(); 
	}
	
	@DeleteMapping("/users/{userId}")
	public void deleteUser(@PathVariable int userId)
	{
		User user = service.deleteUserById(userId);
		if(user == null)
			throw new UserNotFoundException("id :- " + userId);
	}
	
}
