package com.ghostzone.user_service.infrastructure.client;

import com.ghostzone.user_service.domain.interfaces.AlbumGetByIdResponse;
import com.ghostzone.user_service.domain.interfaces.AlbumRequest;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "localhost:8082/album")
public interface AlbumService {
    @PostMapping("/")
    public ResponseEntity<Long> addAlbum(@RequestBody AlbumRequest albumRequest);

    @GetMapping("/{id}")
    public ResponseEntity<AlbumGetByIdResponse> getAlbumById(@PathVariable("id") long albumId);

    @DeleteMapping("/{id}")
    public void deleteAlbum(@PathVariable("id") long albumId);
}
