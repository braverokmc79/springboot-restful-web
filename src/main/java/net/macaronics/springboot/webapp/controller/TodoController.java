package net.macaronics.springboot.webapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.macaronics.springboot.webapp.config.PrincipalDetails;
import net.macaronics.springboot.webapp.dto.todo.TodoFormDTO;
import net.macaronics.springboot.webapp.dto.todo.TodoResponseDTO;
import net.macaronics.springboot.webapp.entity.Todo;
import net.macaronics.springboot.webapp.service.TodoService;
import net.macaronics.springboot.webapp.utils.PageMaker;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor  // 필수 필드를 생성자 주입 방식으로 자동 설정
@RequestMapping("/todo")  // `/todo` 경로에 대한 요청을 처리
public class TodoController {
	
	private final TodoService todoService;  // Todo 관련 비즈니스 로직을 처리하는 서비스 의존성 주입
	
	// 할 일 목록을 보여주는 메서드
	@GetMapping("list-todos")
	public String listAllTodos(@AuthenticationPrincipal PrincipalDetails principal, Model model, PageMaker pageMaker) {		
		int pageInt = pageMaker.getPage() == null ? 0 : pageMaker.getPage();  // 페이지 번호 설정
		PageRequest pageable = PageRequest.of(pageInt, 10);  // 10개의 항목을 한 페이지에 표시
		Page<TodoResponseDTO> todos = todoService.todoListUsername(principal.getUsername(), pageable);  // 현재 사용자 이름으로 할 일 목록 조회
		
		String pagination = pageMaker.pageObject(todos, pageInt, 10, 5, "/todo/list-todos", "href");  // 페이지네이션 처리
	    long totalTodosCount = todos.getTotalElements();  // 전체 할 일 수
		
		model.addAttribute("totalTodosCount", totalTodosCount);  // 할 일 총 개수 전달
		model.addAttribute("todos", todos);  // 할 일 목록 전달
		model.addAttribute("pagination", pagination);  // 페이지네이션 정보 전달
		return "todo/listTodos";  // 뷰 템플릿 경로 반환
	}
	
	// 새로운 할 일 추가 페이지를 보여주는 메서드
	@GetMapping("add-todo")
	public String showNewTodoPage(@ModelAttribute("todoFormDTO") TodoFormDTO todoFormDTO, ModelMap model) {		
		return "todo/addTodo";  // 새로운 할 일 추가 페이지 반환
	}
	
	// 새로운 할 일을 추가하는 메서드
	@PostMapping("add-todo")
	public String addNewTodo(@Valid TodoFormDTO todoFormDTO, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principal) {		
		if (bindingResult.hasErrors()) {  // 입력 값 검증에서 오류가 발생하면
			return "todo/addTodo";  // 할 일 추가 페이지로 다시 이동
		}
		// 새로운 할 일 추가
		todoService.addTodo(principal.getId(), todoFormDTO.getDescription(), todoFormDTO.getTargetDate(), todoFormDTO.isDone());
		return "redirect:/todo/list-todos";  // 할 일 목록 페이지로 리다이렉트
	}
	
	// 할 일을 삭제하는 메서드
	@PostMapping("delete-todo")
	public String deleteTodo(@AuthenticationPrincipal PrincipalDetails principal, Todo todo) {
		TodoResponseDTO gotTodo = todoService.findByIdAndUsername(todo.getId(), principal.getId());  // 할 일을 조회
		if (!principal.getUsername().equals(gotTodo.getUsername())) {  // 사용자 인증 실패 시 로그아웃 처리
			return "redirect:/logout";
		}
		todoService.deleteById(todo.getId());  // 할 일을 삭제
		return "redirect:/todo/list-todos";  // 할 일 목록 페이지로 리다이렉트
	}
	
	// 할 일 수정 페이지를 보여주는 메서드
	@GetMapping("update-todo")
	public String updateTodoPage(@RequestParam Long id, @AuthenticationPrincipal PrincipalDetails principal, Model model) {
		TodoResponseDTO todo = todoService.findByIdAndUsername(id, principal.getId());  // 할 일 조회
		model.addAttribute("todoFormDTO", todo);  // 할 일 정보를 모델에 추가
		return "todo/updateTodo";  // 할 일 수정 페이지 반환
	}
	
	// 할 일을 수정하는 메서드
	@PostMapping("update-todo")
	public String updateTodo(@Valid TodoFormDTO todoFormDTO, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principal) {
		if (bindingResult.hasErrors()) {  // 입력 값 검증에서 오류가 발생하면
			return "todo/updateTodo";  // 할 일 수정 페이지로 다시 이동
		}
		// 할 일 수정
		todoService.updateTodo(todoFormDTO.getId(), principal.getId(), todoFormDTO.getDescription(), todoFormDTO.getTargetDate(), todoFormDTO.isDone());
		return "redirect:/todo/list-todos";  // 할 일 목록 페이지로 리다이렉트
	}
}
