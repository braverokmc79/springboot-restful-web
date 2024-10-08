package net.macaronics.springboot.webapp.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.macaronics.springboot.webapp.dto.todo.TodoCreateDTO;
import net.macaronics.springboot.webapp.dto.todo.TodoResponseDTO;
import net.macaronics.springboot.webapp.dto.todo.TodoUpdateDTO;
import net.macaronics.springboot.webapp.entity.Todo;
import net.macaronics.springboot.webapp.entity.User;
import net.macaronics.springboot.webapp.exception.ResourceNotFoundException;
import net.macaronics.springboot.webapp.mapper.TodoMapper;
import net.macaronics.springboot.webapp.repository.TodoRepository;
import net.macaronics.springboot.webapp.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional  // 이 서비스의 모든 메서드는 기본적으로 트랜잭션 내에서 실행됨
@RequiredArgsConstructor  // 필수 필드의 생성자를 자동으로 생성해주는 Lombok 어노테이션
public class TodoService {

    private final UserRepository userRepository;  // User 정보를 관리하는 Repository
    private final TodoRepository todoRepository;  // Todo 정보를 관리하는 Repository
    
    private final TodoMapper todoMapper;
    

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
    public void deleteById(Long todoId) {
        todoRepository.deleteById(todoId);  // ID로 할 일 삭제
    }

    // 특정 할 일을 ID와 사용자 ID로 조회하여 DTO로 반환하는 메서드
    @Transactional(readOnly = true)
    public TodoResponseDTO findByIdAndUsername(Long todoId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);  // User ID로 조회, 없으면 예외 발생
        Todo todo = todoRepository.findByIdAndUser(todoId, user);  // 할 일을 ID와 사용자로 조회
        return todoMapper.convertTodoResponseDTO(todo);  // Todo를 DTO로 변환 후 반환
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
    
    

    
    /**
     *------------------------------- restFull  -------------------------------------------------------------
    
     */
        
    /**
     * todo 목록 가져오기
     * @param userId
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
	public Page<TodoResponseDTO> getTodosByUserId(Long userId, PageRequest pageable) {	
		return todoRepository.findByUserId(userId, pageable).map(todoMapper::convertTodoResponseDTO);
	}
	
    
    
    /**
     * todo 상세정보 가져오기
     * @param userId
     * @param todoId
     * @return
     */
    @Transactional(readOnly = true)
	public TodoResponseDTO getTodoById(Long userId, Long todoId) {
		Todo todo=todoRepository.findByIdAndUserId(todoId, userId).orElseThrow(
				()->new ResourceNotFoundException("Todo not found"));		
		return todoMapper.convertTodoResponseDTO(todo); 
	}
	
    

    /**
     * todo 저장하기
     * @param userId
     * @param createDTO
     * @return
     */
    public TodoResponseDTO createTodo(Long userId, TodoCreateDTO createDTO) {
    	User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException(userId +" 유저를 찾츨 수 없습니다."));
    	    	
    	Todo todo=todoMapper.ofTodo(createDTO);
    		 todo.setUser(user);
    		 todo.setDone(false);
    		 
        Todo savedTodo = todoRepository.save(todo);
    	return todoMapper.convertTodoResponseDTO(savedTodo);    	
    }
    
    
    /**
     *  todo Update
     * @param userId
     * @param todoId
     * @param updateDTO
     * @return
     */
    public TodoResponseDTO updateTodo(Long userId, Long todoId, TodoUpdateDTO updateDTO) {
        Todo todo = todoRepository.findByIdAndUserId(todoId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found"));

        //더티 체킹
        todo.setDescription(updateDTO.getDescription());
        todo.setTargetDate(updateDTO.getTargetDate());
        todo.setDone(updateDTO.isDone());

        return todoMapper.convertTodoResponseDTO(todo);    
    }

    
	
	/**
	 * todo 삭제
	 * @param userId
	 * @param todoId
	 */
	public void deleteTodo(Long userId, Long todoId) {
		  Todo todo = todoRepository.findByIdAndUserId(todoId, userId)
	                .orElseThrow(() -> new ResourceNotFoundException("Todo not found"));
		  todoRepository.delete(todo);
	}
    
    
    
    
  
	
}

