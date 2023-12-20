package com.ghostzone.recomendation_service.api.controller;

import com.ghostzone.recomendation_service.domain.interfaces.app.RecomendationService;
import com.ghostzone.recomendation_service.domain.model.SongGetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {
    @Autowired
    private RecomendationService recomendationService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<SongGetResponse>> getHistoryByUserId(@PathVariable("userId") long userId){
        List<SongGetResponse> histories = recomendationService.getRecommendations(userId);
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }
}
