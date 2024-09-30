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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;

import net.macaronics.springboot.webapp.dto.ResponseDTO;
import net.macaronics.springboot.webapp.dto.user.RegisterFormDTO;
import net.macaronics.springboot.webapp.dto.user.UserResponse;
import net.macaronics.springboot.webapp.entity.User;
import net.macaronics.springboot.webapp.service.UserService;
import net.macaronics.springboot.webapp.utils.PageMaker;

import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class ApiUserController {

    private final UserService userService;  
    
    /** HATEOAS 링크 추가 메서드 */
    private UserResponse addUserLinks(UserResponse userResponse) {
        userResponse.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ApiUserController.class).getUserById(userResponse.getId())).withSelfRel());
        return userResponse;
    }
    
    
    /**  http://localhost:8080/api/users
     * 1. 전체 사용자 목록 조회
     * @param pageMaker
     * @return
     */
    @Operation(summary = "전체 사용자 목록 조회", description = "모든 사용자의 목록을 페이지네이션하여 조회합니다.")
    @GetMapping
    public ResponseEntity<?> userListAll(@Parameter(description = "페이지 정보", example = "0") PageMaker pageMaker){
        int pageInt = pageMaker.getPage() == null ? 0 : pageMaker.getPage();
        PageRequest pageable = PageRequest.of(pageInt, 6);
                 
        Page<UserResponse> pageUserResponse = userService.userListAll(pageable);
         
        // 1. 각 사용자에 대한 HATEOAS 링크 추가
        List<UserResponse> userList = pageUserResponse.getContent().stream().map(this::addUserLinks).collect(Collectors.toList());
 
        CollectionModel<UserResponse> collectionModel = CollectionModel.of(userList);
        
        // 2. CollectionModel을 사용하여 사용자 목록과 링크 추가
        collectionModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ApiUserController.class).userListAll(pageMaker)).withSelfRel());
        
        return ResponseEntity.ok()
            .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
            .body(ResponseDTO.builder()
                .code(1)
                .message("success")
                .data(collectionModel)
                .build()
            );
    }
    
    
    /**   http://localhost:8080/api/users/{id}
     * 2. 개별 사용자 조회 메소드 
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "개별 사용자 조회 메소드", description = "개별 사용자의 정보를 제공합니다.")
    public ResponseEntity<?> getUserById(@Parameter(description = "아이디", example = "1") @PathVariable Long id){
    	UserResponse user = addUserLinks(userService.getUserById(id));
        return ResponseEntity.ok(user);
    }
    
    
    /** http://localhost:8080/api/users/
     * 3. 사용자 생성 (회원가입) 메소드
     * @param registerFormDTO
     * @param bindingResult
     * @return
     */
    @PostMapping
    @Operation(summary = "사용자 생성", description = "새 사용자를 등록합니다.")
    public ResponseEntity<?> createUser(@Valid @RequestBody RegisterFormDTO registerFormDTO, BindingResult bindingResult) {
        
        // 입력 검증 오류 처리
        if (bindingResult.hasErrors()) { 
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        
        // 중복 사용자 확인
        if (userService.findByUsername(registerFormDTO.getUsername()) != null) {  // 이미 존재하는 아이디일 경우
            bindingResult.rejectValue("username", "error.username", "이미 사용 중인 아이디입니다.");              
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        
        // DTO를 User 객체로 변환 후 저장
        User user = RegisterFormDTO.toCreateUser(registerFormDTO);  // DTO를 User 객체로 변환
        User savedUser = userService.saveUser(user);  // 사용자 저장
        
        // 생성된 사용자의 URI 생성
        URI location = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ApiUserController.class).getUserById(savedUser.getId())).toUri();
        UserResponse userResponse = addUserLinks(UserResponse.of(savedUser));
        
        return ResponseEntity.created(location)
            .body(ResponseDTO.builder()
                .code(1)
                .message("success")
                .data(userResponse)
                .build()
            );
    }
    
    
    
   
    
    
    
    
    
    
    
    
    
}
