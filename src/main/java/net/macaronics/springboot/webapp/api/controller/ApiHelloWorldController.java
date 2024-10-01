package net.macaronics.springboot.webapp.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

@RestController
@RequestMapping("/api/hello")
public class ApiHelloWorldController {

	
	
	@GetMapping
	public String index() {		
		return "Hello World";
	}
	
	
}
