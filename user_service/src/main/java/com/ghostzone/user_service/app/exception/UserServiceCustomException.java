package com.ghostzone.user_service.app.exception;

import lombok.Data;

@Data
public class UserServiceCustomException extends RuntimeException{
    private String errorCode;
    public UserServiceCustomException(String message, String errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
