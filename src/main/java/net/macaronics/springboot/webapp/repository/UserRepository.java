package net.macaronics.springboot.webapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import net.macaronics.springboot.webapp.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);

}

