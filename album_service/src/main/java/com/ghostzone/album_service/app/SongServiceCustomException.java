package com.ghostzone.album_service.app;

import lombok.Data;

@Data
public class SongServiceCustomException extends RuntimeException{
    private String errorCode;
    public SongServiceCustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}