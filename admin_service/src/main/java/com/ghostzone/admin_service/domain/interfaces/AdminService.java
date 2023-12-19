package com.ghostzone.admin_service.domain.interfaces;

import com.ghostzone.admin_service.domain.models.UpdateUserPermissionDTO;
import com.ghostzone.admin_service.domain.models.UserDTO;

public interface AdminService {
    void deleteAlbumById(long id);
    UserDTO updateUserArtist(String email, UpdateUserPermissionDTO updateUserPermissionDTO);
    UserDTO updateUserAdmin(String email, UpdateUserPermissionDTO updateUserPermissionDTO);
}
