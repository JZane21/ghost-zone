package com.ghostzone.history_service.app;

import com.ghostzone.history_service.domain.entity.History;
import com.ghostzone.history_service.domain.interfaces.app.HistoryService;
import com.ghostzone.history_service.domain.interfaces.infrastructure.HistoryRepository;
import com.ghostzone.history_service.domain.model.HistoryByUserIdResponse;
import com.ghostzone.history_service.domain.model.HistoryRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;
    @Override
    public long addHistory(HistoryRequest historyRequest) {
        log.info("History Service: Creating History with id" + historyRequest.getUserId()+" "+historyRequest.getSongId());

        List<History> histories = historyRepository.findAll();
        List<History> filteredHistories = histories
                .stream()
                .filter(history ->
                        history.getUserId()==historyRequest.getUserId() &&
                                history.getSongId()==historyRequest.getSongId())
                .collect(Collectors.toList());
        int count = filteredHistories.size();

        if(count==0){
            History history = History.builder()
                    .songId(historyRequest.getSongId())
                    .userId(historyRequest.getUserId())
                    .build();
            historyRepository.save(history);
            return history.getHistoryId();
        } else{
            return filteredHistories.get(0).getHistoryId();
        }
    }

    @Override
    public List<HistoryByUserIdResponse> getHistoryByUserId(long userId) {
        log.info("History Service: Getting History with id " + userId);

        List<History> histories = historyRepository.findAll();
        List<HistoryByUserIdResponse> filteredHistories = histories
                .stream()
                .filter(history ->
                        history.getUserId()==userId)
                .map(history ->{
                    HistoryByUserIdResponse historyResponse = new HistoryByUserIdResponse();
                    BeanUtils.copyProperties(history, historyResponse);
                    return historyResponse;
                })
                .collect(Collectors.toList());
        return filteredHistories;

    }
}
