package com.ghostzone.admin_service.domain.models;

import jakarta.validation.constraints.Min;
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
public class AlbumRequest {
    @Min(value = 1, message = "El codigo debería tener al menos 1 dígito de extensión")
    @NotNull(message = "El código del album no debería ser nulo")
    @NotEmpty(message = "El código del album no debería estar vacío")
    private long albumId;
}
