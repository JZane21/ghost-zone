package com.ghostzone.user_service.app.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    @NotNull(message = "El username no deberia ser nulo")
    @NotEmpty(message = "El username no deberia estar vacio")
    private String username;
    @NotNull(message = "La contraseña no deberia ser nula")
    @NotEmpty(message = "La contraseña no deberia estar vacia")
    private String password;
}
