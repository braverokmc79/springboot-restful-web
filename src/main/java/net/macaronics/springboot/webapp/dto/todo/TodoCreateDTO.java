package net.macaronics.springboot.webapp.dto.todo;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TodoCreateDTO {

	
	 @NotBlank(message = "내용은 필수 입니다.")
	 @Size(min = 5, max = 35, message = "글자는 10자에서 35자 사이여야 합니다.")
	 private String description;
	    
	 
	 @NotNull(message = "목표 날짜는 필수입니다.")
	 private LocalDate targetDate;
	    
	    
}
