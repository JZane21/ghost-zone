package com.ghostzone.recomendation_service.infrastructure.services;

import com.ghostzone.recomendation_service.domain.model.HistoryByUserIdResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "HISTORY-SERVICE/history")
public interface HistoryService {
    @GetMapping("/{userId}")
    public ResponseEntity<List<HistoryByUserIdResponse>> getHistoryByUserId(@PathVariable("userId") long userId);
}