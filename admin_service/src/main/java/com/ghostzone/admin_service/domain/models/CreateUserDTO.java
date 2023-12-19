package com.ghostzone.admin_service.domain.models;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class CreateUserDTO {
    private String username;
    private String password;
    private String email;
    private boolean admin;
    private boolean artist;
    private String description;
}
