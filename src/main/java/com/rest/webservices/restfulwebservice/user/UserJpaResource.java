package com.rest.webservices.restfulwebservice.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.webservices.restfulwebservice.exception.UserNotFoundException;
import com.rest.webservices.restfulwebservice.jpa.PostRepository;
import com.rest.webservices.restfulwebservice.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
	private UserDaoService service;
	private UserRepository repo;
	private PostRepository Prepo;
	
	public UserJpaResource(UserDaoService service , UserRepository repository, PostRepository prepo) {
		this.service = service;
		this.repo = repository;
		this.Prepo = prepo;
	}
	@GetMapping("/jpa/users")
	public List<User> retrieveUsers(){
		return repo.findAll();
	
	}
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveOneUser(@PathVariable int id) {
		Optional<User> user = repo.findById(id);
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		EntityModel<User> entityModel = EntityModel.of(user.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;

	}
	
	//POST /user
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User saved = repo.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
												  .path("/{id}")
												  .buildAndExpand(saved.getId()).toUri();
		return ResponseEntity.created(location).build();
		
		
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void DeleteUser(@PathVariable int id) {
		repo.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id,@RequestBody Post post){
		Optional<User> user = repo.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}
		post.setUser(user.get());
		Post saved = Prepo.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				  .path("/{id}")
				  .buildAndExpand(saved.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
}
