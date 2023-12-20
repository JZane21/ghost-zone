package com.ghostzone.song_service.app;

import com.ghostzone.song_service.domain.entity.Song;
import com.ghostzone.song_service.domain.interfaces.app.SongService;
import com.ghostzone.song_service.domain.interfaces.infrastructure.SongRepository;
import com.ghostzone.song_service.domain.model.*;
import com.ghostzone.song_service.infrastructure.services.HistoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class SongServiceImpl implements SongService {

    @Autowired
    private HistoryService historyService;

    @Autowired
    private SongRepository songRepository;
    @Override
    public long addSong(SongRequest songRequest) {
        log.info("Song Service: Creating Song with id" + songRequest.getSongName());
        Song song = Song.builder()
                .songName(songRequest.getSongName())
                .artistId(songRequest.getArtistId())
                .albumId(songRequest.getAlbumId())
                .cover(songRequest.getCover())
                .genre(songRequest.getGenre())
                .file(songRequest.getFile())
                .build();

        songRepository.save(song);
        log.info("Song Created");
        return song.getSongId();
    }

    @Override
    public SongGetByIdResponse getSongById(long songId) {

        log.info("Getting Song by Id");
        Song song = songRepository.findById(songId)
                .orElseThrow(
                        () -> new SongServiceCustomException("Product Error No existe este error", "404")
                );

        SongGetByIdResponse songResponse = new SongGetByIdResponse();
        BeanUtils.copyProperties(song, songResponse);

        return songResponse;
    }

    @Override
    public List<SongGetResponse> getAll() {

        log.info("Getting All Songs");
        List<Song> songs = songRepository.findAll();
        List<SongGetResponse> songsResponse = songs
                .stream()
                .map(song ->{
                    SongGetResponse songResponse = new SongGetResponse();
                    BeanUtils.copyProperties(song, songResponse);
                    return songResponse;
                }).collect(Collectors.toList());
        return songsResponse;
    }

    @Override
    public List<SongGetByIdResponse> getAllInternal() {
        log.info("Getting All Songs");
        List<Song> songs = songRepository.findAll();
        List<SongGetByIdResponse> songsResponse = songs
                .stream()
                .map(song ->{
                    SongGetByIdResponse songResponse = new SongGetByIdResponse();
                    BeanUtils.copyProperties(song, songResponse);
                    return songResponse;
                }).collect(Collectors.toList());
        return songsResponse;
    }

    @Override
    public List<SongGetResponse> search(String search) {
        log.info("Searching songs");
        List<Song> songs = songRepository.findAll();
        List<SongGetResponse> songsResponse = songs
                .stream()
                .filter(song -> song.getSongName().contains(search))
                .map(song ->{
                    SongGetResponse songResponse = new SongGetResponse();
                    BeanUtils.copyProperties(song, songResponse);
                    return songResponse;
                }).collect(Collectors.toList());
        return songsResponse;
    }

    @Override
    public SongListenResponse listenToSong(long songId, long userId) {
        log.info("Listening to Song");
        Song song = songRepository.findById(songId)
                .orElseThrow(
                        () -> new SongServiceCustomException("Product Error No existe este error", "404")
                );

        SongListenResponse songResponse = new SongListenResponse();
        BeanUtils.copyProperties(song, songResponse);

        HistoryRequest historyRequest = HistoryRequest.builder()
                .userId(userId)
                .songId(songId)
                .build();

        log.info("Posting to history");
        historyService.addHistory(historyRequest);

        return songResponse;
    }

    @Override
    public void updateSongCover(UpdateCoverRequest updateCoverRequest, long songId) {
        log.info("Updating cover");
        Song song = songRepository.findById(songId)
                .orElseThrow(
                        () -> new SongServiceCustomException("Product Error No existe este error", "404")
                );
        song.setCover(updateCoverRequest.getCover());
        songRepository.save(song);
    }

    @Override
    public void deleteSongById(long id) {
        log.info("Deleting Song");
        songRepository.deleteById(id);
    }
}
