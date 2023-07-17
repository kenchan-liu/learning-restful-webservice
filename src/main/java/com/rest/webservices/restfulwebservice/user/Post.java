package com.rest.webservices.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import jakarta.persistence.GeneratedValue;

@Entity
public class Post {
	@Id
	@GeneratedValue
	private Integer Id;
	@Size(min=10)
	private String description;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	@Override
	public String toString() {
		return "Post [Id=" + Id + ", description=" + description + "]";
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Post(Integer id, String description, User user) {
		super();
		Id = id;
		this.user = user;
		this.description = description;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
