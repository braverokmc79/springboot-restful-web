package net.macaronics.springboot.webapp.service;

public class HelloWorldBean {

	private String message;
	
	public HelloWorldBean(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
}
