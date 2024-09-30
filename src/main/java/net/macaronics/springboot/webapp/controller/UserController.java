package net.macaronics.springboot.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.macaronics.springboot.webapp.dto.RegisterFormDTO;
import net.macaronics.springboot.webapp.entity.User;
import net.macaronics.springboot.webapp.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")  // `/user` 경로에 대한 요청을 처리
public class UserController {

    @Autowired
    private UserService userService;  // User 관련 비즈니스 로직을 처리하는 서비스 의존성 주입

    // 로그인 페이지를 보여주는 메서드
    @GetMapping("/login")
    public String showLoginPage() {
        return "user/login";  // 로그인 페이지 반환
    }

    // 회원가입 페이지를 보여주는 메서드
    @GetMapping("/register")
    public String showRegisterPage(@ModelAttribute("registerFormDTO") RegisterFormDTO registerFormDTO) {
        return "user/register";  // 회원가입 페이지 반환
    }

    // 회원가입을 처리하는 메서드
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("registerFormDTO") RegisterFormDTO registerFormDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {  // 입력 값 검증에서 오류가 발생하면
            return "user/register";  // 회원가입 페이지로 다시 이동
        }
		
        if (userService.findByUsername(registerFormDTO.getUsername()) != null) {  // 이미 존재하는 아이디일 경우
            bindingResult.rejectValue("username", "error.username", "이미 사용 중인 아이디입니다.");
            return "user/register";  // 회원가입 페이지로 다시 이동
        }

        User user = RegisterFormDTO.toCreateUser(registerFormDTO);  // DTO를 User 객체로 변환
        userService.saveUser(user);  // 사용자 저장

        return "redirect:/user/login";  // 로그인 페이지로 리다이렉트
    }
    
    
}
