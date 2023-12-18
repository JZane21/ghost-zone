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
public class AlbumRequest {
    private String albumName;
    private long artistId;
    private String cover;
    private String file;
    private List<SongRequest> songIds;
    private List<String> genre;
}
