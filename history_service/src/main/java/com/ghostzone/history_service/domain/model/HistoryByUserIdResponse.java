package com.ghostzone.history_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryByUserIdResponse {
    private long historyId;
    private long songId;
    private long userId;
}
