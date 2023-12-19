package com.ghostzone.album_service.controller;

import com.ghostzone.album_service.domain.interfaces.app.AlbumService;
import com.ghostzone.album_service.domain.model.AlbumGetByIdResponse;
import com.ghostzone.album_service.domain.model.AlbumGetResponse;
import com.ghostzone.album_service.domain.model.AlbumRequest;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "Agregar un álbum",
            description = "Agrega un nuevo álbum al servicio",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name="albumName", required = true
                            , schema = @Schema(type = "string")
                    ),
                    @Parameter(in = ParameterIn.QUERY, name="cover", required = true
                            , schema = @Schema(type = "string")
                    ),
                    @Parameter(in = ParameterIn.QUERY, name="songs", required = true
                            , schema = @Schema(type = "List<SongRequest>")
                    ),
                    @Parameter(in = ParameterIn.QUERY, name="genre", required = true
                            , schema = @Schema(type = "List<String>")
                    )
            },
            requestBody = @RequestBody(content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlbumRequest.class), examples = {
                            @io.swagger.v3.oas.annotations.media.ExampleObject(name = "example1", value = "{\n" +
                                    "  \"albumName\": \"Nombre del Álbum\",\n" +
                                    "  \"artistId\": 123,\n" +
                                    "  \"cover\": \"https://example.com/album_cover.jpg\",\n" +
                                    "  \"songs\": [\n" +
                                    "    {\n" +
                                    "      \"songName\": \"Canción 1\",\n" +
                                    "      \"artistId\": 456,\n" +
                                    "      \"albumId\": 789,\n" +
                                    "      \"cover\": \"https://example.com/song_cover.jpg\",\n" +
                                    "      \"file\": \"https://example.com/song.mp3\",\n" +
                                    "      \"genre\": [\"Pop\", \"Rock\"]\n" +
                                    "    }\n" +
                                    "  ],\n" +
                                    "  \"genre\": [\"Pop\", \"Rock\"]\n" +
                                    "}")
                    })
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Álbum creado exitosamente",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Solicitud inválida"
                    )
            }
    )
    public ResponseEntity<Long> addSong(@RequestBody AlbumRequest songRequest){
        long songId = albumService.addAlbum(songRequest);
        return new ResponseEntity<>(songId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumGetByIdResponse> getAlbumById(@PathVariable("id") long albumId){
        AlbumGetByIdResponse songResponse = albumService.getAlbumById(albumId);
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

    @DeleteMapping("/{id}")
    public void deleteAlbum(@PathVariable("id") long albumId){
        albumService.deleteAlbum(albumId);
    }
}
