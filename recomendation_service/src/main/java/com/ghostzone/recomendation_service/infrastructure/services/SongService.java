package com.ghostzone.recomendation_service.infrastructure.services;

import com.ghostzone.recomendation_service.domain.model.SongGetByIdResponse;
import com.ghostzone.recomendation_service.domain.model.SongGetResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "SONG-SERVICE/song")
public interface SongService {
    @GetMapping("/{id}")
    public ResponseEntity<SongGetByIdResponse> getSongById(@PathVariable("id") long songId);
    @GetMapping("/internal")
    public ResponseEntity<List<SongGetByIdResponse>> getAllSongs();
}