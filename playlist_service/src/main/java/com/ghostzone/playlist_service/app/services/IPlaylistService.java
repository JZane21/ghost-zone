package com.ghostzone.playlist_service.app.services;

import com.ghostzone.playlist_service.domain.entity.Playlist;
import com.ghostzone.playlist_service.domain.models.PlaylistRequest;
import com.ghostzone.playlist_service.domain.models.PlaylistResponse;

import java.util.List;

public interface IPlaylistService {
        Playlist createPlaylist(PlaylistRequest playListRequest);

        List<Playlist> getAllPlaylist();

}
