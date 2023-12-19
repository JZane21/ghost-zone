package com.ghostzone.playlist_service.domain.models;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistResponse {

    private String playlistName;
    private String cover;
    private long userId; //TODO: Change to user Object
    private List<Long> songIds; // TODO: Change to song Lists
}
