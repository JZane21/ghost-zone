package com.ghostzone.playlist_service.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistRequest {
    private String playlistName;
    private String cover;
    private long userId; //TODO: Change to user Object
    private String songList; // TODO: Change to song Lists
}