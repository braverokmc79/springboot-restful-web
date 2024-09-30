package net.macaronics.springboot.webapp.entity;

import java.time.LocalDate;

import net.macaronics.springboot.webapp.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")  // 테이블 이름을 "users"로 설정
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ID 자동 생성 전략을 사용 (Auto Increment)
    @Column(name = "user_id")
    private Long id;
    
    @Column(nullable = false, unique = true)  // 사용자 이름은 null 불가, 유니크
    private String username;
    
    private String password;  // 사용자 비밀번호

    
    private LocalDate birthDate;
    
    
    @Enumerated(EnumType.STRING)  // Role enum을 문자열로 저장
    private Role role;  // 사용자 권한 (USER, ADMIN 등)
    
    
    public User(String username, LocalDate birthDate) {
    	this.password="$2a$10$8VKmqNwV0x/bQEN8Z54w7uUpLPTGeHgoJR73dyH2S6ZxoHkVkxGSm";
		this.username = username;		
		this.birthDate = birthDate;
	}
    
}
