package com.springboot.webapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.webapp.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);

}

