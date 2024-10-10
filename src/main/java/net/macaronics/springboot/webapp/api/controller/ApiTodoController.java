package net.macaronics.springboot.webapp.api.controller;


import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.macaronics.springboot.webapp.dto.ResponseDTO;
import net.macaronics.springboot.webapp.dto.todo.TodoCreateDTO;
import net.macaronics.springboot.webapp.dto.todo.TodoResponseDTO;
import net.macaronics.springboot.webapp.dto.todo.TodoUpdateDTO;
import net.macaronics.springboot.webapp.service.TodoService;
import net.macaronics.springboot.webapp.utils.PageMaker;

@RestController
@RequestMapping("/api/users/{userId}/todos")
@RequiredArgsConstructor
@Slf4j
public class ApiTodoController {

    private final TodoService todoService;

    /**
     * HATEOAS 링크 추가 메서드
     */
    private TodoResponseDTO addTodoLinks(TodoResponseDTO todoResponse) {
        todoResponse.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ApiTodoController.class)
        		.getTodoById(todoResponse.getUserId(), todoResponse.getId()))
                .withSelfRel());
        return todoResponse;
    }

    /**
     * 1. GET /api/users/{userId}/todos
     *    전체 Todo 목록 조회
     */
    @Operation(summary = "전체 Todo 목록 조회", description = "특정 사용자의 모든 Todo를 페이지네이션하여 조회합니다.")
    @GetMapping
    public ResponseEntity<?> getAllTodos(@PathVariable Long userId,@Valid PageMaker pageMaker) {
    	log.info(" /api/users/{userId}/todos   :", userId);
    	
        int pageInt = pageMaker.getPage() == null ? 0 : pageMaker.getPage();
        PageRequest pageable = PageRequest.of(pageInt, 10); // 예: 페이지당 10개

        Page<TodoResponseDTO> todoPage = todoService.getTodosByUserId(userId, pageable);

        List<TodoResponseDTO> todosWithLinks = todoPage.getContent().stream().map(this::addTodoLinks).collect(Collectors.toList());

        CollectionModel<TodoResponseDTO> collectionModel = CollectionModel.of(todosWithLinks);
        collectionModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ApiTodoController.class).getAllTodos(userId, pageMaker)).withSelfRel());

        //.cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
        return ResponseEntity.ok()
                .body(ResponseDTO.builder()
                        .code(1)
                        .message("success")
                        .data(collectionModel)
                        .build());
    }
    
    

    /**
     * 2. GET /api/users/{userId}/todos/{todoId}
     *    개별 Todo 조회
     */
    @Operation(summary = "개별 Todo 조회", description = "특정 사용자의 특정 Todo를 조회합니다.")
    @GetMapping("/{todoId}")
    public ResponseEntity<?> getTodoById(
            @PathVariable Long userId,
            @PathVariable Long todoId) {

        TodoResponseDTO todo = todoService.getTodoById(userId, todoId);
        addTodoLinks(todo);

        return ResponseEntity.ok(todo);
    }

    
    /**
     * 3. POST /api/users/{userId}/todos
     *    Todo 생성
     */
    @Operation(summary = "Todo 생성", description = "특정 사용자에게 새로운 Todo를 생성합니다.")
    @PostMapping
    public ResponseEntity<?> createTodo(
            @PathVariable Long userId,
            @Valid @RequestBody TodoCreateDTO todoCreateDTO,
            BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }

        TodoResponseDTO createdTodo = todoService.createTodo(userId, todoCreateDTO);
        addTodoLinks(createdTodo);

        URI location = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ApiTodoController.class).getTodoById(userId, createdTodo.getId())).toUri();

        return ResponseEntity.created(location)
                .body(ResponseDTO.builder()
                        .code(1)
                        .message("Todo created successfully")
                        .data(createdTodo)
                        .build());
    }

    
    
    /**
     * 4. PUT /api/users/{userId}/todos/{todoId}
     *    Todo 수정
     */
    @Operation(summary = "Todo 수정", description = "특정 사용자의 특정 Todo를 수정합니다.")
    @PutMapping("/{todoId}")
    public ResponseEntity<?> updateTodo(
            @PathVariable Long userId,
            @PathVariable Long todoId,
            @Valid @RequestBody TodoUpdateDTO todoUpdateDTO,
            BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }

        TodoResponseDTO updatedTodo = todoService.updateTodo(userId, todoId, todoUpdateDTO);
        addTodoLinks(updatedTodo);

        return ResponseEntity.ok(ResponseDTO.builder()
                .code(1)
                .message("Todo updated successfully")
                .data(updatedTodo)
                .build());
    }

    
    
    /**
     * 5. DELETE /api/users/{userId}/todos/{todoId}
     *    Todo 삭제
     */
    @Operation(summary = "Todo 삭제", description = "특정 사용자의 특정 Todo를 삭제합니다.")
    @DeleteMapping("/{todoId}")
    public ResponseEntity<?> deleteTodo(
            @PathVariable Long userId,
            @PathVariable Long todoId) {

        todoService.deleteTodo(userId, todoId);

        CollectionModel<?> collectionModel = CollectionModel.empty();
        collectionModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ApiTodoController.class).getAllTodos(userId, new PageMaker())).withRel("all-todos"));

        ResponseDTO<?> response = ResponseDTO.builder()
                .code(1)
                .message("Todo deleted successfully")
                .data(collectionModel)
                .build();

        return ResponseEntity.ok(response);
    }
    
    
    
}
