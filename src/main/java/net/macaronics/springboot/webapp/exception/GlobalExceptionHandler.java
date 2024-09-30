package net.macaronics.springboot.webapp.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;
import net.macaronics.springboot.webapp.dto.ResponseDTO;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
   
    /**
     * 모든 예외 처리
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleAllExceptions(Exception ex, WebRequest request){
        log.error("Unhandled exception occurred", ex);
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        
        ResponseDTO<?> response = ResponseDTO.builder()
                .code(-1)
                .message("Internal Server Error")
                .data(errorDetails)
                .errorCode("INTERNAL_SERVER_ERROR")
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    /**
     * 공통 404 예외 처리: NotFoundException
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<?> handleNotFoundException(NotFoundException ex, WebRequest request){
        log.warn("Resource not found exception: {}", ex.getMessage());
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        
        ResponseDTO<?> response = ResponseDTO.builder()
            .code(HttpStatus.NOT_FOUND.value())
            .message("Resource Not Found")
            .data(errorDetails)
            .errorCode("NOT_FOUND")
            .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * bindingResult.hasErrors() 에러시 반환 처리한다
     * 유효성 체크 에러 처리
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request){
        List<String> errors = ex.getBindingResult()
                                .getAllErrors()
                                .stream()
                                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                .collect(Collectors.toList());
        
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "Validation Failed", request.getDescription(false));
        log.warn("Validation failed: {}", errorDetails.getMessage());

        ResponseDTO<?> response = ResponseDTO.builder()
            .code(-1)
            .message(errors.get(0))
            .data(errorDetails)
            .errorCode("VALIDATION_ERROR")
            .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    // 기타 특정 예외 핸들러 추가 가능
    
    
    
}
