package com.elearning.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJPAResource {
	
	@Autowired
	private UserDaoService service;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers(){
		return userRepository.findAll(); 
	}
	
	@GetMapping("/jpa/users/{userId}")
	@Operation(description = "Retrieves data of all users from the storage.", summary = "Get All User Data", tags = "Users")
	public User retrieveUserById(@PathVariable int userId)
	{
		Optional<User> optionalUser = userRepository.findById(userId);
		if(!optionalUser.isPresent())
			throw new UserNotFoundException("id :- " + userId);
		Link usersLink = linkTo(methodOn(this.getClass()).retrieveAllUsers()).withRel("all-users");
		User user = optionalUser.get();
		user.add(usersLink);
		return user;
	}
	
	@PostMapping("/jpa/users/{userId}/posts")
	public ResponseEntity<Object> createUser(@PathVariable("userId") int id,@RequestBody Post post)
	{
		Optional<User> optionalUser = userRepository.findById(id);
		if(!optionalUser.isPresent())
			throw new UserNotFoundException("id :- " + id);
		User user = optionalUser.get();
		post.setUser(user);
		postRepository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(location).build(); 
	}
	
	@DeleteMapping("/jpa/users/{userId}")
	public void deleteUser(@PathVariable int userId)
	{
		userRepository.deleteById(userId);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveAllUsersPosts(@PathVariable int id)
	{
		Optional<User> userOptional = userRepository.findById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id :-" + id);
		}
		
		return userOptional.get().getPosts();
	}
	
}
