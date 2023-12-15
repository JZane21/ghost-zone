package com.ghostzone.song_service.app;

import com.ghostzone.song_service.domain.interfaces.app.SongService;
import com.ghostzone.song_service.domain.interfaces.infrastructure.SongRepository;
import com.ghostzone.song_service.domain.model.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;
    @Override
    public long addSong(SongRequest productRequest) {
        return 0;
    }

    @Override
    public SongGetByIdResponse getSongById(long productId) {
        return null;
    }

    @Override
    public List<SongGetResponse> getAll() {
        return null;
    }

    @Override
    public List<SongGetResponse> search(String search) {
        return null;
    }

    @Override
    public SongListenResponse listenToSong(long songId) {
        return null;
    }

    @Override
    public void updateSongCover(UpdateCoverRequest updateCoverRequest) {

    }
}
