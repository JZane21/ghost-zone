package com.ghostzone.recomendation_service.app;

import com.ghostzone.recomendation_service.domain.interfaces.app.RecomendationService;
import com.ghostzone.recomendation_service.domain.model.HistoryByUserIdResponse;
import com.ghostzone.recomendation_service.domain.model.SongGetByIdResponse;
import com.ghostzone.recomendation_service.domain.model.SongGetResponse;
import com.ghostzone.recomendation_service.infrastructure.services.HistoryService;
import com.ghostzone.recomendation_service.infrastructure.services.SongService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class RecomendationServiceImpl implements RecomendationService {

    @Autowired
    private HistoryService historyService;

    @Autowired
    private SongService songService;


    @Override
    public List<SongGetResponse> getRecommendations(long userId) {
        ResponseEntity<List<HistoryByUserIdResponse>> historyUser = historyService.getHistoryByUserId(userId);
        List<HistoryByUserIdResponse> historyUserBody = historyUser.getBody();
        List<List<String>> rawGenres = historyUserBody
                .stream()
                .map(history ->{
                    ResponseEntity<SongGetByIdResponse> song = songService.getSongById(history.getSongId());
                    return song.getBody().getGenre();
                }).collect(Collectors.toList());
        List<String> genres = new ArrayList<>();
        for(List<String> listRaw : rawGenres){
            for(String genreRaw : listRaw){
                genres.add(genreRaw);
            }
        }
        List<SongGetResponse> recommendations = songService.getAllSongs().getBody()
                .stream()
                .filter(song -> {
                    for(String a : song.getGenre()){
                        return genres.contains(a);
                    }
                    return false;
                }).map(song ->{
                    SongGetResponse songResponse = new SongGetResponse();
                    BeanUtils.copyProperties(song, songResponse);
                    return songResponse;
                        }
                ).collect(Collectors.toList());
        return recommendations;
    }
}
