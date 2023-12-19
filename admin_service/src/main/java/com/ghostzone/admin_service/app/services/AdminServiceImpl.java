package com.ghostzone.admin_service.app.services;

import com.ghostzone.admin_service.domain.interfaces.AdminService;
import com.ghostzone.admin_service.domain.models.CreateUserDTO;
import com.ghostzone.admin_service.domain.models.UpdateUserPermissionDTO;
import com.ghostzone.admin_service.domain.models.UserDTO;
import com.ghostzone.admin_service.infraestructure.external.AlbumService;
import com.ghostzone.admin_service.infraestructure.external.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserService userService;

    @Autowired
    private AlbumService albumService;

    @Override
    public void deleteAlbumById(long id) {
        albumService.deleteSelectedAlbum(id);
    }

    @Override
    public UserDTO updateUserArtist(String email, UpdateUserPermissionDTO updateUserPermissionDTO) {
        UserDTO userDTO = userService.getUser(email);
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setUsername("");
        createUserDTO.setDescription("");
        createUserDTO.setPassword("");
        createUserDTO.setEmail("");
        createUserDTO.setAdmin(userDTO.isAdmin());
        createUserDTO.setArtist(updateUserPermissionDTO.isArtist());
        return userService.updateUser(email, createUserDTO);
    }

    @Override
    public UserDTO updateUserAdmin(String email, UpdateUserPermissionDTO updateUserPermissionDTO) {
        UserDTO userDTO = userService.getUser(email);
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setUsername("");
        createUserDTO.setDescription("");
        createUserDTO.setPassword("");
        createUserDTO.setEmail("");
        createUserDTO.setAdmin(updateUserPermissionDTO.isAdmin());
        createUserDTO.setArtist(userDTO.isArtist());
        return userService.updateUser(email, createUserDTO);
    }

}
