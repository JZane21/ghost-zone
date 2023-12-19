package com.ghostzone.album_service.app;

import com.ghostzone.album_service.domain.entity.Album;
import com.ghostzone.album_service.domain.interfaces.app.AlbumService;
import com.ghostzone.album_service.domain.interfaces.infrastructure.AlbumRepository;
import com.ghostzone.album_service.domain.model.*;
import com.ghostzone.album_service.infrastructure.services.SongService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private SongService songService;


    @Override
    public long addAlbum(AlbumRequest albumRequest) {
        log.info("Album Service: Creating Album with id" + albumRequest.getAlbumName());
        Album album = Album.builder()
                .albumName(albumRequest.getAlbumName())
                .artistId(albumRequest.getArtistId())
                .cover(albumRequest.getCover())
                .songIds(new ArrayList<>())
                .genre(albumRequest.getGenre())
                .build();

        albumRepository.save(album);
        log.info("Album Created");
        log.info("Posting Songs");
        List<Long> songIds = albumRequest.getSongs()
                .stream()
                .map(
                song ->{
                    SongServiceRequest songRequest = SongServiceRequest.builder()
                            .songName(song.getSongName())
                            .artistId(albumRequest.getArtistId())
                            .albumId(album.getAlbumId())
                            .cover(albumRequest.getCover())
                            .file(song.getFile())
                            .genre(albumRequest.getGenre())
                            .build();
                    ResponseEntity<Long> id = songService.addSong(songRequest);
                    return id.getBody();
                }
        ).collect(Collectors.toList());
        log.info("Updating Album");

        Album updateAlbum = albumRepository.getById(album.getAlbumId());
        updateAlbum.setSongIds(songIds);
        albumRepository.save(updateAlbum);

        return album.getAlbumId();
    }

    @Override
    public AlbumGetByIdResponse getAlbumById(long albumId) {

        log.info("Getting Album by Id");
        Album album = albumRepository.findById(albumId)
                .orElseThrow(
                        () -> new AlbumServiceCustomException("Product Error No existe este error", "404")
                );

        AlbumGetByIdResponse albumGetByIdResponse = new AlbumGetByIdResponse();
        BeanUtils.copyProperties(album, albumGetByIdResponse);

        return albumGetByIdResponse;
    }

    @Override
    public List<AlbumGetResponse> getAll() {

        log.info("Getting All Albums");
        List<Album> albums = albumRepository.findAll();
        List<AlbumGetResponse> albumsResponse = albums
                .stream()
                .map(album ->{
                    AlbumGetResponse albumResponse = new AlbumGetResponse();
                    BeanUtils.copyProperties(album, albumResponse);
                    return albumResponse;
                }).collect(Collectors.toList());
        return albumsResponse;
    }

    @Override
    public List<AlbumGetResponse> search(String search) {
        log.info("Searching albums");
        List<Album> albums = albumRepository.findAll();
        List<AlbumGetResponse> albumsResponse = albums
                .stream()
                .filter(album -> album.getAlbumName().contains(search))
                .map(album ->{
                    AlbumGetResponse albumResponse = new AlbumGetResponse();
                    BeanUtils.copyProperties(album, albumResponse);
                    return albumResponse;
                }).collect(Collectors.toList());
        return albumsResponse;
    }

    @Override
    public void deleteAlbum(long albumId) {
        log.info("Deleting album with Id "+albumId);
        Album album = albumRepository.findById(albumId)
                .orElseThrow(
                        () -> new AlbumServiceCustomException("Product Error No existe este error", "404")
                );
        album.getSongIds()
                .stream()
                .forEach(songId ->
                        songService.deleteSongById(songId)
                );
        albumRepository.deleteById(albumId);
    }
}
