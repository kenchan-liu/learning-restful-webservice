package com.rest.webservices.restfulwebservice.user;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import jakarta.persistence.OneToMany;


@Entity(name = "user_details")
public class User {
	
	protected User(){
		
	}
	
	@Id
	@GeneratedValue
	private Integer id;
	@Size(min=2,message="name should have at least 2 characters")
	//@JsonProperty("user_name")
	private String name;
	@Past(message = "birth date should be in the past")
	//@JsonProperty("birth_date")
	private LocalDate birthdate;
	
	@OneToMany(mappedBy="")
	@JsonIgnore
	private List<Post> posts;
	public User(Integer id, String name, LocalDate birthDate) {
		//super();
		this.id = id;
		this.name = name;
		this.birthdate = birthDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	
	public List<Post> getPosts(){
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", birthdate=" + birthdate + "]";
	}
}
