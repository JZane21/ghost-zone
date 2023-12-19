package com.ghostzone.admin_service.controller;

import com.ghostzone.admin_service.app.services.AdminServiceImpl;
import com.ghostzone.admin_service.domain.models.UpdateUserPermissionDTO;
import com.ghostzone.admin_service.domain.models.UserDTO;
import com.ghostzone.admin_service.infraestructure.external.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;
    @DeleteMapping
    public void deleteAlbum(@RequestParam("id") String id){
        adminService.deleteAlbumById(Long.parseLong(id));
    }

    @PutMapping
    public UserDTO updateUser(@RequestParam("email") String email, @RequestBody UpdateUserPermissionDTO updateUserPermissionDTO){
        if(updateUserPermissionDTO.isUpdateAdmin()){
            return adminService.updateUserAdmin(email, updateUserPermissionDTO);
        }else{
            return adminService.updateUserArtist(email, updateUserPermissionDTO);
        }
    }

}
