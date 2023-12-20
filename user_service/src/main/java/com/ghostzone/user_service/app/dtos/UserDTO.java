package com.ghostzone.user_service.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private long id;
    private String username;
    private String email;
    private String password;
    private boolean admin;
    private boolean artist;
    private String description;
}
