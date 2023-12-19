package com.ghostzone.album_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlbumGetByIdResponse {
    private long albumId;
    private String albumName;
    private long userId;
    private List<Long> songIds;
    private String cover;
    private List<String> genre;
}
