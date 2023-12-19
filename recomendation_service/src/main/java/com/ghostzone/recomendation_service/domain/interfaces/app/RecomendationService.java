package com.ghostzone.recomendation_service.domain.interfaces.app;

import com.ghostzone.recomendation_service.domain.model.SongGetResponse;

import java.util.List;

public interface RecomendationService {
    List<SongGetResponse> getRecommendations(long userId);

}
