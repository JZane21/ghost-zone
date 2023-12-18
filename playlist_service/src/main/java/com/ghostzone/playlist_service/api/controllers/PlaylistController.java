package com.ghostzone.playlist_service.api.controllers;


import com.ghostzone.playlist_service.domain.entity.Playlist;
import com.ghostzone.playlist_service.app.services.IPlaylistService;

//import com.ghostzone.playlist_service.domain.entity.SongRequest;
import com.ghostzone.playlist_service.domain.models.PlaylistRequest;
import com.ghostzone.playlist_service.domain.models.PlaylistResponse;

import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/playlist")
public class PlaylistController {
    @Autowired
    private IPlaylistService iplaylistService;

    @PostMapping()
    public ResponseEntity<Playlist> createPlaylist(@RequestBody PlaylistRequest playlistRequest){
        Playlist playlist = iplaylistService.createPlaylist(playlistRequest);
        return new ResponseEntity<>(playlist, HttpStatusCode.valueOf(201));
    }

    @GetMapping()
    public ResponseEntity<List<Playlist>> getAllPlaylist(){
        log.info("Playlist Controller: Obteniendo todas las  playlist ");
        List<Playlist> playlist = iplaylistService.getAllPlaylist();
        log.debug("Playlist Controller: Returning: " + playlist);
        return new ResponseEntity<>(playlist,HttpStatusCode.valueOf(200));
    }

    @GetMapping("/nombre/{playlistName}")
    public ResponseEntity<List<PlaylistResponse>> getPlayListByName(@PathVariable String playlistName){
        log.info("Playlist Controller: Obteniendo playlist por su nombre ");
        List<PlaylistResponse> playlistResponse= iplaylistService.getPlaylistByName(playlistName);

        return new ResponseEntity<>(playlistResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deletePlaylist(@PathVariable long id){
        log.info("Playlist Controller: Borrando playlist por su ID ");
        String eliminateResponse = iplaylistService.deletePlaylist(id);
        return  new ResponseEntity<>(eliminateResponse,HttpStatus.OK);
    }

    /*
    @PutMapping("/{id}")
    public ResponseEntity<Playlist>addSongToPlaylist(@PathVariable long id, @RequestBody SongRequest songRequest){
        log.info("Playlist Controller: Añadiendo cancion a la playlist por su ID ");
        Playlist newPlaylist = iplaylistService.addSong(id,songRequest);
        return new ResponseEntity<>(newPlaylist,HttpStatus.OK);
    }
     */

//    @DeleteMapping("/{idPlaylist}/{idSong}")
//    public ResponseEntity<Playlist>deletePlaylist(@PathVariable long idPlaylist, @PathVariable String idSong){
//        log.info("Playlist Controller: Borrando playlist por su ID ");
//        Playlist eliminateResponse = iplaylistService.deleteSong(idPlaylist,idSong);
//        return  new ResponseEntity<>(eliminateResponse,HttpStatus.OK);
//    }
}
