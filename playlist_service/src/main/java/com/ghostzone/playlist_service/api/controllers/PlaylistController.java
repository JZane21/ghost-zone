package com.ghostzone.playlist_service.api.controllers;


import com.ghostzone.playlist_service.domain.entity.Playlist;
import com.ghostzone.playlist_service.app.services.IPlaylistService;

import com.ghostzone.playlist_service.domain.models.PlaylistRequest;

import com.ghostzone.playlist_service.domain.models.PlaylistResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
