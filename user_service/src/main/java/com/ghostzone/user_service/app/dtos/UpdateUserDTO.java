package com.ghostzone.user_service.app.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class UpdateUserDTO {
    private String username;
    private String password;
    @NotBlank()
    @NotNull()
    @NotEmpty()
    private boolean admin;
    @NotBlank()
    @NotNull()
    @NotEmpty()
    private boolean artist;
    private String description;
}
