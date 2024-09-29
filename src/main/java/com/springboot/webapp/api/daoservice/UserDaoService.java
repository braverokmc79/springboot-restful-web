package com.springboot.webapp.api.daoservice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.webapp.entity.User;

@Component
public class UserDaoService {

	
	private static List<User> users = new ArrayList<>();
	
	
	static {
		users.add(new User( "Admin", LocalDate.now().minusYears(30)));
		users.add(new User( "Eve", LocalDate.now().minusYears(25)));
		users.add(new User( "Jim", LocalDate.now().minusYears(25)));
	}
	

	public List<User> findAll(){
		return users;
	}
	
	
	
	
	
}
