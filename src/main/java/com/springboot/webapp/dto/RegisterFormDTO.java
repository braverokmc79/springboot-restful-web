package com.springboot.webapp.dto;

import com.springboot.webapp.entity.User;
import com.springboot.webapp.enums.Role;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterFormDTO {
      
    @NotEmpty
    @Size(min = 2, max = 10, message = "아이디는 2자에서 10자 사이여야 합니다.")
    private String username;
        
    @NotEmpty
    @Size(min = 4, max = 15, message = "비밀번호는 4자에서 15자 사이여야 합니다.")
    private String password;

    @NotEmpty(message = "비밀번호 확인이 필요합니다.")
    private String confirmPassword;

    private Role role;

    // 비밀번호와 비밀번호 확인이 일치하는지 확인
    @AssertTrue(message = "비밀번호와 비밀번호 확인이 일치해야 합니다.")
    public boolean isPasswordConfirmed() {
        return password != null && password.equals(confirmPassword);
    }
    
    
    public static User toCreateUser(RegisterFormDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(Role.USER);
        return user;
    }
}

