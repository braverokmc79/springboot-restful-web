package net.macaronics.springboot.webapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "사용자를 찾을 수 없습니다.")
public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -2997057852151179119L;

	public UserNotFoundException(String message) {		
		 super(message + " 에 해당하는 사용자를 찾을 수 없습니다.");
	}

	
}
