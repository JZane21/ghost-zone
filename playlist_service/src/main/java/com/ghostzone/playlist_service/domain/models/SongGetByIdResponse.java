package com.ghostzone.playlist_service.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongGetByIdResponse {
    private long songId;
    private String songName;
    private long artistId;
    private long albumId;
    private String cover;
    private String file;
    private List<String> genre;
}
