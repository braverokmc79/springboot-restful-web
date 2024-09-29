package com.springboot.webapp.api.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.webapp.dto.ResponseDTO;
import com.springboot.webapp.dto.UserResponse;
import com.springboot.webapp.service.UserService;
import com.springboot.webapp.utils.PageMaker;

import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class ApiUserController {

    private final UserService userService;  

    @Operation(summary = "전체 사용자 목록 조회", description = "모든 사용자의 목록을 페이지네이션하여 조회합니다.")
    @GetMapping
    public ResponseEntity<?> userListAll(
        @Parameter(description = "페이지 정보", example = "0") PageMaker pageMaker
    ){
        int pageInt = pageMaker.getPage() == null ? 0 : pageMaker.getPage();
        PageRequest pageable = PageRequest.of(pageInt, 6);
                
        Page<UserResponse> pageUserResponse = userService.userListAll(pageable);
        
        List<UserResponse> userList = pageUserResponse.getContent();              
        return ResponseEntity.ok(
            ResponseDTO.builder()
                .code(1)
                .message("success")
                .data(userList)
                .build()
        );
    }
}
