package net.macaronics.springboot.webapp.dto.todo;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoFormDTO {

	private Long id;

	private Long userId;
	
	private String username;

	@Size(min = 5, max = 35, message = "글자는 10자에서 35자 사이여야 합니다.")
	private String description;

	
	private LocalDate targetDate;

	private boolean done;
	
}
