package com.elearning.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Tag(name = "Users", description = "Users data handlers.")
@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return service.findAll(); 
	}
	
	@GetMapping("/users/{userId}")
	@Operation(description = "Retrieves data of all users from the storage.", summary = "Get All User Data", tags = "Users")
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
