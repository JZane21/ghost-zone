package com.ghostzone.song_service.domain.model;

import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongRequest {
    @NotNull(message = "El nombre de la canción no puede ser nulo")
    @NotEmpty(message = "El nombre de la canción no puede estar vacío")
    private String songName;

    @NotNull(message = "El id no puede ser nulo")
    @NotEmpty(message = "El id puede estar vacío")
    private long userId;

    @NotNull(message = "El id no puede ser nulo")
    @NotEmpty(message = "El id puede estar vacío")
    private long albumId;

    @NotNull(message = "La URL de la portada no puede ser nula")
    @NotEmpty(message = "La URL de la portada no puede estar vacía")
    private String cover;

    @NotNull(message = "La URL del archivo no puede ser nula")
    @NotEmpty(message = "La URL del archivo no puede estar vacía")
    private String file;

    @NotNull(message = "La lista de géneros no puede ser nula")
    @NotEmpty(message = "La lista de géneros no puede estar vacía")
    @Length(min = 1, message = "Debe haber al menos un género")
    private List<String> genre;
}
