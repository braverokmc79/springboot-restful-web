package net.macaronics.springboot.webapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseDTO<T> {

	private int code;  //1(성공), -1(실패)
	private String message;
	private String errorCode;
	private T data;
	
	
}
