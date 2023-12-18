package com.ghostzone.playlist_service.app.services;

import com.ghostzone.playlist_service.domain.entity.Playlist;
import com.ghostzone.playlist_service.domain.models.PlaylistRequest;
import com.ghostzone.playlist_service.domain.models.PlaylistResponse;
//import com.ghostzone.playlist_service.domain.entity.SongRequest;

import java.util.List;

public interface IPlaylistService {
        Playlist createPlaylist(PlaylistRequest playListRequest);

        List<Playlist> getAllPlaylist();

        List<PlaylistResponse> getPlaylistByName(String playlistName);

        String deletePlaylist(long id);

        //Playlist addSong(long id,SongRequest songRequest);

        //Playlist deleteSong(long idPlaylist,long idSong);
}
