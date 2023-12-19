package com.ghostzone.admin_service.domain.models;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class UpdateUserPermissionDTO {
    private boolean updateAdmin;
    private boolean admin;
    private boolean artist;
}
