package com.ghostzone.user_service.infrastructure.client;

import com.ghostzone.user_service.domain.interfaces.SongGetByIdResponse;
import com.ghostzone.user_service.domain.interfaces.SongRequest;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "SONG-SERVICE/song")
public interface SongService {
    @PostMapping("/")
    public ResponseEntity<Long> addSong(@RequestBody SongRequest songRequest);

    @GetMapping("/{id}")
    public ResponseEntity<SongGetByIdResponse> getSongById(@PathVariable("id") long songId);

    @DeleteMapping("/{id}")
    public void deleteSongById(@PathVariable("id") long songId);
}
