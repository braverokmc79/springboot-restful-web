package net.macaronics.springboot.webapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/")
public class HelloWorldController {


	//http://localhost:8080/test/hello
	@GetMapping("/hello")
	public String hello(String helloStr){
		return "hello";
	}

	
	
}
