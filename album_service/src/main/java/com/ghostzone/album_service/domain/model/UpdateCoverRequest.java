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
public class UpdateCoverRequest {
    @NotNull(message = "La cobertura no puede ser nula")
    @NotEmpty(message = "La cobertura no puede estar vacía")
    private String cover;
}
