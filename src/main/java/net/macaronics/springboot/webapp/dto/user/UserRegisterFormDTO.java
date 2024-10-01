package net.macaronics.springboot.webapp.dto.user;


import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.macaronics.springboot.webapp.entity.User;
import net.macaronics.springboot.webapp.enums.Role;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterFormDTO {
    
    @NotEmpty(message = "아이디는 필수 입력 항목입니다.")
    @Size(min = 2, max = 10, message = "아이디는 2자에서 10자 사이여야 합니다.")
    private String username;

    @NotEmpty(message = "비밀번호는 필수 입력 항목입니다.")
    @Size(min = 4, max = 15, message = "비밀번호는 4자에서 15자 사이여야 합니다.")
    private String password;

    @NotEmpty(message = "비밀번호 확인이 필요합니다.")
    private String confirmPassword;

    @Past(message = "생일은 과거 날짜로 입력해야 합니다.")
    private LocalDate birthDate;
    
    private Role role = Role.USER; // 기본값 설정

    // 비밀번호와 비밀번호 확인이 일치하는지 확인
    @AssertTrue(message = "비밀번호와 비밀번호 확인이 일치해야 합니다.")
    public boolean isPasswordConfirmed() {
        return password != null && password.equals(confirmPassword);
    }
    
    public static User toCreateUser(UserRegisterFormDTO dto) {
        return User.builder()
            .username(dto.getUsername())
            .password(dto.getPassword())
            .birthDate(dto.getBirthDate())
            .role(dto.getRole() != null ? dto.getRole() : Role.USER) // role이 null인 경우 기본값 설정
            .build();
    }
    
    
}
