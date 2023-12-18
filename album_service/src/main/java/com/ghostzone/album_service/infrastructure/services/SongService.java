package com.ghostzone.album_service.infrastructure.services;

import com.ghostzone.album_service.domain.model.SongServiceRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "SONG-SERVICE/product")
public interface SongService {
    @PostMapping("/") // localhost:8080/id/quantity PUT
    public ResponseEntity<Long> addSong(@RequestBody SongServiceRequest songRequest);
}