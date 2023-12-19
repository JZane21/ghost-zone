package com.ghostzone.admin_service.domain.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class UpdateUserPermissionDTO {
    @NotNull
    @NotEmpty
    private boolean updateAdmin;
    @NotNull
    @NotEmpty
    private boolean admin;
    @NotNull
    @NotEmpty
    private boolean artist;
}
