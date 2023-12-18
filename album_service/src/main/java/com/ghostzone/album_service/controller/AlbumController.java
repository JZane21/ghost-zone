package com.ghostzone.album_service.controller;

import com.ghostzone.album_service.domain.interfaces.app.AlbumService;
import com.ghostzone.album_service.domain.model.AlbumGetByIdResponse;
import com.ghostzone.album_service.domain.model.AlbumGetResponse;
import com.ghostzone.album_service.domain.model.AlbumRequest;
import com.ghostzone.album_service.domain.model.UpdateCoverRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @PostMapping
    public ResponseEntity<Long> addSong(@RequestBody AlbumRequest songRequest){
        long songId = albumService.addAlbum(songRequest);
        return new ResponseEntity<>(songId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumGetByIdResponse> getAlbumById(@PathVariable("id") long songId){
        AlbumGetByIdResponse songResponse = albumService.getAlbumById(songId);
        return new ResponseEntity<>(songResponse, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<AlbumGetResponse>> search(@RequestParam(name ="search", required = false) String search){
        List<AlbumGetResponse> songResponse;
        if (search==null) {
            songResponse = albumService.getAll();
        } else{
            songResponse = albumService.search(search);
        }

        return new ResponseEntity<>(songResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public void updateSongCover(@PathVariable("id") long songId, @RequestBody UpdateCoverRequest updateCoverRequest) {
        albumService.updateSongCover(updateCoverRequest);
    }
}
