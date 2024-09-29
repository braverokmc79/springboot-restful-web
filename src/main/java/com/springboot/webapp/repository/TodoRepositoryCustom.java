package com.springboot.webapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.webapp.dto.TodoResponseDTO;
import com.springboot.webapp.entity.User;


public interface TodoRepositoryCustom {

	Page<TodoResponseDTO> todoListUsername(User user, Pageable pageable);
    
}