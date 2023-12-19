package com.ghostzone.user_service.api.controller;

import com.ghostzone.user_service.app.dtos.CreateUserDTO;
import com.ghostzone.user_service.app.dtos.UserDTO;
import com.ghostzone.user_service.app.dtos.UserLoginDTO;
import com.ghostzone.user_service.app.services.AuthService;
import com.ghostzone.user_service.domain.interfaces.UserService;
import com.ghostzone.user_service.infrastructure.utils.AuthenticatorUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticatorUser authenticatorUser;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> addNewUser(@RequestBody CreateUserDTO createUserDTO){
        UserDTO userDTO = this.userService.postUser(createUserDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> getToken(@RequestBody UserLoginDTO userLoginDto){
        if(this.authenticatorUser.isAuthenticated(
                userLoginDto.getUsername(), userLoginDto.getPassword()
        )){
            return new ResponseEntity<>("Invalid user access",HttpStatus.BAD_REQUEST);
        }
        String token = service.generateToken(userLoginDto.getUsername());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token){
        service.validateToken(token);
        return new ResponseEntity<>("Token validated", HttpStatus.OK);
    }

}
