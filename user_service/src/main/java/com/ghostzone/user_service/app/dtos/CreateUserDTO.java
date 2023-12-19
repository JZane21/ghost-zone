package com.ghostzone.user_service.app.dtos;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class CreateUserDTO {
    @NotNull()
    @NotEmpty()
    private String username;
    @NotNull()
    @NotEmpty()
    private String password;
    @NotNull()
    @NotEmpty()
    private String email;
    @NotEmpty()
    private boolean admin;
    @NotEmpty()
    private boolean artist;
    @NotNull()
    @NotEmpty()
    private String description;
}
