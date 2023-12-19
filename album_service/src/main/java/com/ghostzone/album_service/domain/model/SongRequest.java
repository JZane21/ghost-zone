package com.ghostzone.album_service.domain.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongRequest {
    @NotNull(message = "El nombre de la canción no puede ser nulo")
    @NotEmpty(message = "El nombre de la canción no puede estar vacio")
    private String songName;
    @NotNull(message = "El archivo no puede ser nulo")
    @NotEmpty(message = "El archivo no puede estar vacio")
    private String file;
}
