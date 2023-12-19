package com.ghostzone.playlist_service.domain.models;

//import com.ghostzone.playlist_service.domain.entity.SongRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistRequest {
    private String playlistName;
    private String cover;
    private long userId; //TODO: Change to user Object
    private List<Long> songIds; // TODO: Change to song Lists
}
