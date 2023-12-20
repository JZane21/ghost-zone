package com.ghostzone.song_service.infrastructure.services;

import com.ghostzone.song_service.domain.model.HistoryRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "HISTORY-SERVICE/history")
public interface HistoryService {
    @PostMapping("/")
    public ResponseEntity<Long> addHistory(@RequestBody HistoryRequest historyRequest);
}
