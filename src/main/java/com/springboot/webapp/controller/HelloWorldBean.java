package com.springboot.webapp.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class HelloWorldBean {

	private String helloStr;
	
	
	public HelloWorldBean(String helloStr){
		this.helloStr = helloStr;
	}

	
}
