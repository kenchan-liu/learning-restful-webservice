package com.rest.webservices.restfulwebservice.helloworld;

public class HelloworldBean {
	private String message;
	public HelloworldBean(String message) {
		// TODO Auto-generated constructor stub
		this.setMessage(message);
		
	
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "HelloworldBean [message=" + message + "]";
	}

}
