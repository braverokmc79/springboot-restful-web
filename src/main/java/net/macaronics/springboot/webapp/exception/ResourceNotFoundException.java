package net.macaronics.springboot.webapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "찾을 수 없습니다.")
public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -2997057852151179119L;

	public ResourceNotFoundException(String message) {		
		 super(message);
	}

	
}
