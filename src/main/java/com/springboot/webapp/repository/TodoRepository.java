package com.springboot.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.webapp.entity.Todo;
import com.springboot.webapp.entity.User;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoRepositoryCustom {

	Todo findByIdAndUser(Long id, User user);

}
