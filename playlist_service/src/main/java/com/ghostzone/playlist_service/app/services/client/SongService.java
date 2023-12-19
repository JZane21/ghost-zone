package com.ghostzone.playlist_service.app.services.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "PRODUCT-SERVICE/product") // localhost:8081/song
    public interface SongService {
        @GetMapping("/{id}") // localhost:8080/id/quantity GET
        public void discountQuantity(@PathVariable("id") long songId);

}

