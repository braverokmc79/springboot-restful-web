package net.macaronics.springboot.webapp.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloWorldBean {

	private String helloStr;
	
	
	public HelloWorldBean(String helloStr){
		this.helloStr = helloStr;
	}

	
}
