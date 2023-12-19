package com.ghostzone.user_service.api.controller;

import com.ghostzone.user_service.app.dtos.*;
import com.ghostzone.user_service.app.exception.UserServiceCustomException;
import com.ghostzone.user_service.app.services.AuthService;
import com.ghostzone.user_service.domain.interfaces.UserService;
import com.ghostzone.user_service.infrastructure.utils.AuthenticatorUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticatorUser authenticatorUser;
    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestParam("email") String email, @RequestBody CreateUserDTO createUserDTO)
    throws RuntimeException{
        UserDTO existUser = this.userService.getUserByEmail(email);
        if(existUser!=null){

        }
        UserDTO userDTO = this.userService.postUser(createUserDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<UserDTO> getUser(@RequestParam("email") String email, @RequestParam("token") String token){
        authService.validateToken(token);
        UserDTO userDTO = this.userService.getUserByEmail(email);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestParam("email") String email, @RequestParam("token") String token , @RequestParam("myEmail") String myEmail, @RequestBody UpdateUserDTO updateUserDTO){
        authService.validateToken(token);
        UserDTO myUserDTO = this.userService.getUserByEmail(myEmail);
        if(!myUserDTO.isAdmin() && (updateUserDTO.isAdmin() || updateUserDTO.isArtist())){
            throw new UserServiceCustomException("Unauthorized user, admin permission needed","401");
        }
        UserDTO userDTO = this.userService.updateInfoUserByEmail(email, updateUserDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam("email") String email, @RequestParam("token") String token, @RequestBody DeleteUserDTO deleteUserDTO){
        authService.validateToken(token);
        UserDTO userDTO = this.userService.getUserByEmail(email);
        if(this.authenticatorUser.isAuthenticated(
                userDTO.getUsername(), deleteUserDTO.getPassword()
        )){
            throw new UserServiceCustomException("UserController: Unauthorized access, password or email incorrect","403");
        }
        this.userService.deleteUserByEmail(email);
    }

}
