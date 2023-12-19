package com.ghostzone.album_service.app;

import lombok.Data;

@Data
public class AlbumServiceCustomException extends RuntimeException{
    private String errorCode;
    public AlbumServiceCustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}