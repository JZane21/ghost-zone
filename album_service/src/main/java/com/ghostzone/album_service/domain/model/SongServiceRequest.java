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
public class SongServiceRequest {
    private String songName;
    private long artistId;
    private long albumId;
    private String cover;
    private String file;
    private List<String> genre;
}
