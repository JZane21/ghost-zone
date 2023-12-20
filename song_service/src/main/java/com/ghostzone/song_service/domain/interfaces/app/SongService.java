package com.ghostzone.song_service.domain.interfaces.app;

import com.ghostzone.song_service.domain.model.*;

import java.util.List;

public interface SongService {
    long addSong(SongRequest productRequest);

    SongGetByIdResponse getSongById(long productId);

    List<SongGetResponse> getAll();

    List<SongGetByIdResponse> getAllInternal();
    List<SongGetResponse> search(String search);

    SongListenResponse listenToSong(long songId, long userId);

    void updateSongCover(UpdateCoverRequest updateCoverRequest, long songId);

    void deleteSongById(long id);
}
