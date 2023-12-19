package com.ghostzone.playlist_service.app.services;

import com.ghostzone.playlist_service.app.services.client.SongService;
import com.ghostzone.playlist_service.domain.entity.Playlist;
import com.ghostzone.playlist_service.domain.models.PlaylistNotFoundException;

import com.ghostzone.playlist_service.domain.models.PlaylistRequest;
import com.ghostzone.playlist_service.domain.models.PlaylistResponse;
//import com.ghostzone.playlist_service.domain.entity.SongRequest;

import com.ghostzone.playlist_service.domain.models.SongGetByIdResponse;
import com.ghostzone.playlist_service.infraestructure.repository.PlaylistRepository;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class PlaylistServiceImpl implements IPlaylistService{

    @Autowired
    private PlaylistRepository playListRepository;

    @Autowired
    private SongService songService;

    public Playlist createPlaylist(PlaylistRequest playListRequest){
        log.info("PlaylistService: Creando playlist " + playListRequest.getPlaylistName());
        Playlist playList= Playlist.builder()
                .playlistName(playListRequest.getPlaylistName())
                .userId(playListRequest.getUserId())
                .songIds(playListRequest.getSongIds())
                .cover(playListRequest.getCover())
                .build();
        playListRepository.save(playList);
        log.info("playList creada exitosamente!");
        return playList;

    }


    public List<Playlist> getAllPlaylist(){
        log.info("PlaylistService: Obteniendo todas las  playlist ");

        return playListRepository.findAll();
//        List<PlaylistResponse> playlistResponse = new ArrayList<>();
//
//        BeanUtils.copyProperties(playlist, playlistResponse);
//        log.debug("Playlist ServiceImpl: Returning: " + playlistResponse);
//
//        return playlistResponse;+
    }


    public List<PlaylistResponse> getPlaylistByName(String playlistName){
        log.info("PlaylistService: Obteniendo playlists por su nombre ");

        List<PlaylistResponse> playlistResponseList = new ArrayList<>();
        List<Playlist> playlists = playListRepository.findByPlaylistName(playlistName);

        for (Playlist playlist: playlists){
            PlaylistResponse playlistResponse = new PlaylistResponse();
            BeanUtils.copyProperties(playlist, playlistResponse);
            playlistResponseList.add(playlistResponse);
        }

        return playlistResponseList;
    }


    public String deletePlaylist(long id) {
        log.info("PlaylistService: Eliminando playlist por su ID ");

        playListRepository.deleteById(id);
        return "Playlist borrada exitosamente!";
    }



    public Playlist addSong(long playListId, long songId){
        log.info("PlaylistService: Añadiendo una cancion a la playlist");
        Playlist playlist = playListRepository.findById(playListId).orElseThrow(() -> new PlaylistNotFoundException(playListId));
        List<Long> newSongList = playlist.getSongIds();

        SongGetByIdResponse song = songService.getSongById(songId);
        newSongList.add(song.getSongId());
        playlist.setSongIds(newSongList);

        // Guardar la entidad actualizada en la base de datos
        return playListRepository.save(playlist);
    }

    public Playlist deleteSong(long playListId,long songId){
       log.info("PlaylistService: Borrando una cancion de la playlist");
       Playlist playlist = playListRepository.findById(playListId).orElseThrow(() -> new PlaylistNotFoundException(playListId));


       playlist.getSongIds().remove(songId);
       return playlist;
     }


}
