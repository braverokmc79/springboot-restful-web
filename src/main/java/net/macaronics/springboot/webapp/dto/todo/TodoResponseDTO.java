package net.macaronics.springboot.webapp.dto.todo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoResponseDTO {

	private Long id;

	private Long userId;
	
	private String username;

	private String description;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate targetDate;

	private boolean done;
	
	private Long num;  // 목록에서 번호를 매길 필드
	
	 // 매개변수 있는 생성자 추가 (Projections.constructor에서 요구하는 타입)
    public TodoResponseDTO(Long id, Long userId, String username, String description, LocalDate targetDate, Boolean done) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.done = done;
    }
    
}
