package com.ghostzone.user_service.domain.interfaces;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlbumRequest {
    @NotNull(message = "El nombre no puede ser nulo")
    @NotEmpty(message = "El nombre no puede estas vacio")
    private String albumName;
    @NotNull(message = "El id no puede ser nulo")
    @NotEmpty(message = "El id no puede estar vacio")
    private long userId;
    @NotNull(message = "La cubierta no puede ser nulo")
    @NotEmpty(message = "La cubierta no puede estar vacio")
    private String cover;
    @NotNull(message = "La lista de canciones no pueden ser nulos")
    @NotEmpty(message = "La lista de canciones no pueden estar vacios")
    private List<SongRequest> songs;
    @NotNull(message = "Los generos no pueden ser nulo")
    @NotEmpty(message = "Los generos no pueden estar vacios")
    private List<String> genre;
}

