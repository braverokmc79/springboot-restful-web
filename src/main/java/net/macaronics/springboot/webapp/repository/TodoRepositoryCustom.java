package net.macaronics.springboot.webapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.macaronics.springboot.webapp.dto.todo.TodoResponseDTO;
import net.macaronics.springboot.webapp.entity.User;


public interface TodoRepositoryCustom {

	Page<TodoResponseDTO> todoListUsername(User user, Pageable pageable);
    
}