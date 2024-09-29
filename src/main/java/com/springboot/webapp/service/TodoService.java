package com.springboot.webapp.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.webapp.dto.TodoResponseDTO;
import com.springboot.webapp.entity.Todo;
import com.springboot.webapp.entity.User;
import com.springboot.webapp.repository.TodoRepository;
import com.springboot.webapp.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional  // 이 서비스의 모든 메서드는 기본적으로 트랜잭션 내에서 실행됨
@RequiredArgsConstructor  // 필수 필드의 생성자를 자동으로 생성해주는 Lombok 어노테이션
public class TodoService {

    private final UserRepository userRepository;  // User 정보를 관리하는 Repository
    private final TodoRepository todoRepository;  // Todo 정보를 관리하는 Repository

    // 특정 사용자의 할 일 목록을 조회하는 메서드 (읽기 전용 트랜잭션)
    @Transactional(readOnly = true)
    public Page<TodoResponseDTO> todoListUsername(String username, Pageable pageable) {
        User user = userRepository.findByUsername(username);  // username으로 User 조회
        return todoRepository.todoListUsername(user, pageable);  // 사용자별 할 일 목록을 페이지로 반환
    }
        
    // 새로운 할 일을 추가하는 메서드
    public void addTodo(Long userId, String description, LocalDate targetDate, boolean done) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);  // User ID로 조회, 없으면 예외 발생
        Todo todo = Todo.builder()
                        .user(user)  // 할 일을 생성한 사용자 설정
                        .description(description)  // 할 일 설명 설정
                        .targetDate(targetDate)  // 목표 날짜 설정
                        .done(done)  // 완료 여부 설정
                        .build();
        todoRepository.save(todo);  // 할 일을 저장
    }

    // 특정 할 일을 ID로 삭제하는 메서드
    public void deleteById(Long id) {
        todoRepository.deleteById(id);  // ID로 할 일 삭제
    }

    // 특정 할 일을 ID와 사용자 ID로 조회하여 DTO로 반환하는 메서드
    @Transactional(readOnly = true)
    public TodoResponseDTO findByIdAndUsername(Long todoId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);  // User ID로 조회, 없으면 예외 발생
        Todo todo = todoRepository.findByIdAndUser(todoId, user);  // 할 일을 ID와 사용자로 조회
        return Todo.toResponseDTO(todo);  // Todo를 DTO로 변환 후 반환
    }
    
    // 할 일을 수정하는 메서드 (더티 체킹을 통해 자동으로 변경 사항이 반영됨)
    public void updateTodo(Long todoId, Long userId, String description, LocalDate plusYears, boolean done) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);  // User ID로 조회, 없으면 예외 발생
        Todo todo = todoRepository.findByIdAndUser(todoId, user);  // 할 일을 ID와 사용자로 조회
        todo.setDescription(description);  // 할 일 설명 수정
        todo.setTargetDate(plusYears);  // 목표 날짜 수정
        todo.setDone(done);  // 완료 여부 수정
        // 더티 체킹을 통해 수정된 필드들은 자동으로 저장됨
    }
}
