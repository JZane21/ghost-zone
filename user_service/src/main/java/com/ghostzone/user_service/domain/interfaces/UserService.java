package com.ghostzone.user_service.domain.interfaces;

import com.ghostzone.user_service.app.dtos.CreateUserDTO;
import com.ghostzone.user_service.app.dtos.UpdateUserDTO;
import com.ghostzone.user_service.app.dtos.UserDTO;

public interface UserService {
    UserDTO postUser(CreateUserDTO createUserDTO);
    UserDTO getUserByEmail(String email);
    UserDTO updateInfoUserByEmail(String email, UpdateUserDTO updateUserDTO);
    void deleteUserByEmail(String email);

}
