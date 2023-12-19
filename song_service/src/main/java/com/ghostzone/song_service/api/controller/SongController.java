package com.ghostzone.song_service.api.controller;

import com.ghostzone.song_service.domain.interfaces.app.SongService;
import com.ghostzone.song_service.domain.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
    @Operation(
            summary = "Agregar una canción",
            description = "Agrega una nueva canción al servicio",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name="songName", required = true
                            , schema = @Schema(type = "string")
                            ),
                    @Parameter(in = ParameterIn.QUERY, name="userEmail", required = true
                            , schema = @Schema(type = "string")
                    ),
                    @Parameter(in = ParameterIn.QUERY, name="albumId", required = true
                            , schema = @Schema(type = "long")
                    ),
                    @Parameter(in = ParameterIn.QUERY, name="cover", required = true
                            , schema = @Schema(type = "string")
                    ),
                    @Parameter(in = ParameterIn.QUERY, name="file", required = true
                            , schema = @Schema(type = "string")
                    ),
                    @Parameter(in = ParameterIn.QUERY, name="genre", required = true
                            , schema = @Schema(type = "List<String>")
                    )
            },
            requestBody = @RequestBody(content = @Content(mediaType = "application/json", schema = @Schema(implementation = SongRequest.class), examples = {
                    @ExampleObject(name = "example1", value = "{\"songName\": \"Example Song\", \"artistId\": \"email@email.com\", \"albumId\": 456, \"cover\": \"https://example.com/cover.jpg\", \"file\": \"https://example.com/song.mp3\", \"genre\": [\"Pop\", \"Rock\"]}")
            })),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Canción creada exitosamente"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Solicitud inválida"
                    )
            }
    )
    public ResponseEntity<Long> addSong(
            @RequestBody SongRequest songRequest){
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
