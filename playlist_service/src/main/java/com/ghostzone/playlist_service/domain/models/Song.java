package com.ghostzone.playlist_service.domain.models;

import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Song {

    private long songId;
    private String songName;
    private long artistId;
    private long albumId;
    private String cover;
    private List<String> genre;
    private String file;

}
