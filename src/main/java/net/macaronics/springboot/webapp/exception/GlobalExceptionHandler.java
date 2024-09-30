package net.macaronics.springboot.webapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import net.macaronics.springboot.webapp.dto.ResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseDTO<?>> handleUserNotFoundException(UserNotFoundException ex) {
        ResponseDTO<?> response = ResponseDTO.builder()
            .code(404)
            .message(ex.getMessage())
            .data(null)
            .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // 기타 예외 핸들러 추가 가능
}
