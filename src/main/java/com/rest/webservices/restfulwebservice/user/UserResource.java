package com.rest.webservices.restfulwebservice.user;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.webservices.restfulwebservice.exception.UserNotFoundException;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class UserResource {
	private UserDaoService service;
	
	public UserResource(UserDaoService service) {
		this.service = service;
	}
	@GetMapping("/users")
	public List<User> retrieveUsers(){
		return service.findAll();
	
	}
	@GetMapping("users/{id}")
	public EntityModel<User> retrieveOneUser(@PathVariable int id) {
		User user = service.findOne(id);
		if(user == null)
			throw new UserNotFoundException("id:"+id);
		EntityModel<User> entityModel = EntityModel.of(user);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;

	}
	
	//POST /user
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User saved = service.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
												  .path("/{id}")
												  .buildAndExpand(saved.getId()).toUri();
		return ResponseEntity.created(location).build();
		
		
	}
	
	@DeleteMapping("users/{id}")
	public void DeleteUser(@PathVariable int id) {
		service.deleteById(id);
	}
}
