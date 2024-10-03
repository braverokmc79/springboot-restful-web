package net.macaronics.springboot.webapp.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.macaronics.springboot.webapp.entity.Todo;
import net.macaronics.springboot.webapp.entity.User;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoRepositoryCustom {

	Todo findByIdAndUser(Long id, User user);

	void deleteByUserId(Long id);
	
	
	Page<Todo> findByUserId(Long userId, Pageable pageable);
	
	Optional<Todo> findByIdAndUserId(Long id, Long userId);

	
}
