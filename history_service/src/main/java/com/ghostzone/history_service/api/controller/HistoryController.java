package com.ghostzone.history_service.api.controller;

import com.ghostzone.history_service.domain.interfaces.app.HistoryService;
import com.ghostzone.history_service.domain.model.HistoryByUserIdResponse;
import com.ghostzone.history_service.domain.model.HistoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @PostMapping("/")
    public ResponseEntity<Long> addHistory(@RequestBody HistoryRequest historyRequest){
        long historyId = historyService.addHistory(historyRequest);
        return new ResponseEntity<>(historyId, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<HistoryByUserIdResponse>> getHistoryByUserId(@PathVariable("userId") long userId){
        List<HistoryByUserIdResponse> histories = historyService.getHistoryByUserId(userId);
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }
}
