package com.ghostzone.user_service.app.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class DeleteUserDTO {
    @NotNull(message = "La contraseña no debería ser nula")
    @NotEmpty(message = "La contraseña no debería estar vacía")
    private String password;
}
