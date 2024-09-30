package net.macaronics.springboot.webapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import net.macaronics.springboot.webapp.dto.ResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseDTO<?>> handleNotFoundException(NotFoundException ex) {
        ResponseDTO<?> response = ResponseDTO.builder()
            .code(-1)
            .message(ex.getMessage())
            .data(null)
            .errorCode("NOT_FOUND")
            .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    
    // 기타 예외 핸들러 추가 가능
}
