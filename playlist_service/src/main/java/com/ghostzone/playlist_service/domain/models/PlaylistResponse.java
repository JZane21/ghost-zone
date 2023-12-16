package com.ghostzone.playlist_service.domain.models;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistResponse {

    private String playlistName;
    private String cover;
    private long userId; //TODO: Change to user Object
    private String songList; // TODO: Change to song Lists
}
