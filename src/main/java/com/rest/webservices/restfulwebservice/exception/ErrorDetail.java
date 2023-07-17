package com.rest.webservices.restfulwebservice.exception;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ErrorDetail {
	private LocalDate timestamp;
	private String memory;
	private String detail;
	public ErrorDetail(LocalDate timestamp, String memory, String detail) {
		super();
		this.timestamp = timestamp;
		this.memory = memory;
		this.detail = detail;
	}
	public LocalDate getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDate timestamp) {
		this.timestamp = timestamp;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
