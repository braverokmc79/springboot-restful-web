package net.macaronics.springboot.webapp.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import net.macaronics.springboot.webapp.dto.user.UserUpdateFormDTO;
import net.macaronics.springboot.webapp.enums.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)  // 삭제된 자식 엔티티 제거
    private List<Todo> todos = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    public User(String username, LocalDate birthDate) {
        this.password = "$2a$10$8VKmqNwV0x/bQEN8Z54w7uUpLPTGeHgoJR73dyH2S6ZxoHkVkxGSm";
        this.username = username;
        this.birthDate = birthDate;
    }

    // 더티 체킹 업데이트
    public static User updateUser(User user, UserUpdateFormDTO updateFormDTO) {
        user.setPassword(updateFormDTO.getPassword());  // 암호화 로직 추가 고려
        user.setBirthDate(updateFormDTO.getBirthDate());
        user.setRole(updateFormDTO.getRole());
        return user;
    }
}
