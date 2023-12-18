package com.ghostzone.album_service.app;

import com.ghostzone.album_service.domain.interfaces.app.AlbumService;
import com.ghostzone.album_service.domain.interfaces.infrastructure.AlbumRepository;
import com.ghostzone.album_service.domain.model.AlbumGetByIdResponse;
import com.ghostzone.album_service.domain.model.AlbumGetResponse;
import com.ghostzone.album_service.domain.model.AlbumRequest;
import com.ghostzone.album_service.domain.model.UpdateCoverRequest;
import com.ghostzone.song_service.domain.entity.Song;
import com.ghostzone.song_service.domain.interfaces.app.SongService;
import com.ghostzone.song_service.domain.interfaces.infrastructure.SongRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository songRepository;
    @Override
    public long addAlbum(AlbumRequest songRequest) {
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
    public AlbumGetByIdResponse getSongById(long songId) {

        log.info("Getting Song by Id");
        Song song = songRepository.findById(songId)
                .orElseThrow(
                        () -> new SongServiceCustomException("Product Error No existe este error", "404")
                );

        AlbumGetByIdResponse songResponse = new SongGetByIdResponse();
        BeanUtils.copyProperties(song, songResponse);

        return songResponse;
    }

    @Override
    public List<AlbumGetResponse> getAll() {

        log.info("Getting All Songs");
        List<Song> songs = songRepository.findAll();
        List<SongGetResponse> songsResponse = songs
                .stream()
                .map(song ->{
                    SongGetResponse songResponse = new SongGetResponse();
                    BeanUtils.copyProperties(song, songResponse);
                    return songResponse;
                }).collect(Collectors.toList());
    }

    @Override
    public List<AlbumGetResponse> search(String search) {
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
    }

    @Override
    public void updateSongCover(UpdateCoverRequest updateCoverRequest) {
        log.info("Updating cover");
        Song song = songRepository.findById(songId)
                .orElseThrow(
                        () -> new SongServiceCustomException("Product Error No existe este error", "404")
                );
        song.setCover(updateCoverRequest.getCover());
        songRepository.save(song);
    }
}
