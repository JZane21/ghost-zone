package com.ghostzone.song_service.domain.interfaces.app;

import com.ghostzone.song_service.domain.model.*;

import java.util.List;

public interface SongService {
    long addSong(SongRequest productRequest);

    SongGetByIdResponse getSongById(long productId);

    List<SongGetResponse> getAll();
    List<SongGetResponse> search(String search);

    SongListenResponse listenToSong(long songId);

    void updateSongCover(UpdateCoverRequest updateCoverRequest);
}
