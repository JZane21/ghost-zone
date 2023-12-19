package com.ghostzone.song_service.api.controller;

import com.ghostzone.song_service.domain.interfaces.app.SongService;
import com.ghostzone.song_service.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {
    @Autowired
    private SongService songService;

    @PostMapping("/")
    public ResponseEntity<Long> addSong(@RequestBody SongRequest songRequest){
        long songId = songService.addSong(songRequest);
        return new ResponseEntity<>(songId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongGetByIdResponse> getSongById(@PathVariable("id") long songId){
        SongGetByIdResponse songResponse = songService.getSongById(songId);
        return new ResponseEntity<>(songResponse, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<SongGetResponse>> searchSong(@RequestParam(name ="search", required = false) String search){
        List<SongGetResponse> songResponse;
        if (search==null) {
            songResponse = songService.getAll();
        } else{
            songResponse = songService.search(search);
        }

        return new ResponseEntity<>(songResponse, HttpStatus.OK);
    }

    @GetMapping("/internal")
    public ResponseEntity<List<SongGetByIdResponse>> getAllSongs(){
        List<SongGetByIdResponse> songResponse = songService.getAllInternal();

        return new ResponseEntity<>(songResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}/file")
    public ResponseEntity<SongListenResponse> listenToSong(@PathVariable("id") long songId){
        SongListenResponse songResponse = songService.listenToSong(songId);
        return new ResponseEntity<>(songResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public void updateSongCover(@PathVariable("id") long songId, @RequestBody UpdateCoverRequest updateCoverRequest) {
        songService.updateSongCover(updateCoverRequest, songId);
    }

    @DeleteMapping("/{id}")
    public void deleteSongById(@PathVariable("id") long songId){
        songService.deleteSongById(songId);
    }
}
