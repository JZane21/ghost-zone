package com.ghostzone.playlist_service.app.services.client;

import com.ghostzone.playlist_service.domain.models.SongGetByIdResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "SONG-SERVICE/song") // localhost:8081/song
    public interface SongService {
        @GetMapping("/{songId}") // localhost:8081/id GET
        public SongGetByIdResponse getSongById(@PathVariable("songId") long songId);

}

