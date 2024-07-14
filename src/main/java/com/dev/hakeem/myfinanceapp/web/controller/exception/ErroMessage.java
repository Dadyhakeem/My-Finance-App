package com.dev.hakeem.myfinanceapp.web.controller.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
@Getter
@ToString
public class ErroMessage {

    private String path;
    private String  method;
    private int Status;
    private String statusText;
    private  String message;
    private Map <String,String>erros;

    public ErroMessage() {
    }

    public  ErroMessage(HttpServletRequest request, HttpStatus status, String message){
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.Status = status.value();
        this.statusText = status.getReasonPhrase();
        this.message = message;
    }

    public  ErroMessage(HttpServletRequest request, HttpStatus status, String message, BindingResult result){
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.Status = status.value();
        this.statusText = status.getReasonPhrase();
        this.message = message;
        addErros(result);
    }

    private void addErros(BindingResult result) {
        this.erros= new HashMap<>();
        for (FieldError fieldError : result.getFieldErrors()){
            this.erros.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
    }
}