package com.ghostzone.album_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlbumGetResponse {
    private String albumName;
    private long userId;
    private String cover;
}
