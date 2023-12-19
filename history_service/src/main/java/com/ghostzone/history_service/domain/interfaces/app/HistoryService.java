package com.ghostzone.history_service.domain.interfaces.app;

import com.ghostzone.history_service.domain.model.HistoryByUserIdResponse;
import com.ghostzone.history_service.domain.model.HistoryRequest;

import java.util.List;

public interface HistoryService {
    long addHistory(HistoryRequest historyRequest);

    List<HistoryByUserIdResponse> getHistoryByUserId(long userId);
}
