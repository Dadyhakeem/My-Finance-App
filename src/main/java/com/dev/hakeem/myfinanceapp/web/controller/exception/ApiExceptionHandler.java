package com.dev.hakeem.myfinanceapp.web.controller.exception;

import com.dev.hakeem.myfinanceapp.exception.UserEmailUniqueViolationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroMessage> methodArgumentNotValidException(MethodArgumentNotValidException e
            , HttpServletRequest request, BindingResult result){
            log.error("Api Error",e);
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ErroMessage(request,HttpStatus.UNPROCESSABLE_ENTITY,"Campo(s) invalida",result));

    }

    @ExceptionHandler(UserEmailUniqueViolationException.class)
    public ResponseEntity<ErroMessage> methodArgumentNotValidException(UserEmailUniqueViolationException e
            , HttpServletRequest request){
        log.error("Api Error",e);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErroMessage(request,HttpStatus.CONFLICT, e.getMessage()));

    }
}
