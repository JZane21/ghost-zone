package com.ghostzone.playlist_service.domain.models;

public class PlaylistNotFoundException extends RuntimeException {
        public PlaylistNotFoundException(long id){
            super(String.valueOf(id));
        }
}
