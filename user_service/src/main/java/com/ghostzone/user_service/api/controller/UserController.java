package com.ghostzone.user_service.api.controller;

import com.ghostzone.user_service.app.dtos.*;
import com.ghostzone.user_service.app.exception.UserServiceCustomException;
import com.ghostzone.user_service.app.services.AuthService;
import com.ghostzone.user_service.domain.interfaces.*;
import com.ghostzone.user_service.infrastructure.utils.AuthenticatorUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/user")
@Log4j2
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticatorUser authenticatorUser;
    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestParam("id") Long id, @RequestBody CreateUserDTO createUserDTO)
    throws RuntimeException{
        UserDTO existUser = this.userService.getUserById(id);
        UserDTO userDTO = this.userService.postUser(createUserDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<UserDTO> getUser(@RequestParam("id") Long id, @RequestParam("token") String token){
        authService.validateToken(token);
        UserDTO userDTO = this.userService.getUserById(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestParam("id") Long id, @RequestParam("token") String token, @RequestBody UpdateUserDTO updateUserDTO){
        authService.validateToken(token);
        UserDTO userDTO = this.userService.updateInfoUserById(id, updateUserDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam("id") Long id, @RequestParam("token") String token, @RequestBody DeleteUserDTO deleteUserDTO){
        authService.validateToken(token);
        UserDTO userDTO = this.userService.getUserById(id);
        if(!this.authenticatorUser.isAuthenticated(
                userDTO.getUsername(), deleteUserDTO.getPassword()
        )){
            throw new UserServiceCustomException("UserController: Unauthorized access, password or email incorrect","403");
        }
        this.userService.deleteUserById(id);
    }

    @GetMapping("/song/{id}")
    public ResponseEntity<SongGetByIdResponse> getMySong(@RequestParam("token") String token,
             @PathVariable("id") long songId){
        this.authService.validateToken(token);
        return this.userService.getMySongById(songId);
    }

    @DeleteMapping("/song")
    public void deleteMySong(@RequestParam("token") String token,
                             @RequestParam("userId") long userId,
                             @RequestParam("songId") long songId){
        this.authService.validateToken(token);
        ResponseEntity<SongGetByIdResponse> song = this.userService.getMySongById(songId);
        boolean condition = userId == song.getBody().getUserId();
        if(!condition){
            throw new UserServiceCustomException("Unauthorized access","401");
        }
        this.userService.deleteMySongById(songId);
    }

    @PostMapping("/song")
    public ResponseEntity<Long> addMySong(@RequestParam("token") String token, @RequestBody SongRequest songRequest){
        this.authService.validateToken(token);
        return this.userService.addMyNewSong(songRequest);
    }

    @GetMapping("/album/{id}")
    public ResponseEntity<SongGetByIdResponse> getAlbum(@RequestParam("token") String token,
                                                         @PathVariable("id") long songId){
        this.authService.validateToken(token);
        return this.userService.getMySongById(songId);
    }

    @DeleteMapping("/album")
    public void deleteAlbum(@RequestParam("token") String token,
                             @RequestParam("userId") long userId,
                             @RequestParam("albumId") long albumId){
        this.authService.validateToken(token);
        ResponseEntity<AlbumGetByIdResponse> song = this.userService.getMyAlbumById(albumId);
        boolean condition = userId == song.getBody().getUserId();
        if(!condition){
            throw new UserServiceCustomException("Unauthorized access","401");
        }
        this.userService.deleteAlbumById(albumId);
    }

    @PostMapping("/album")
    public ResponseEntity<Long> addAlbum(@RequestParam("token") String token, @RequestBody AlbumRequest albumRequest){
        log.info("INGRESANDO RESPECTIVAMENTE A NUESTRO REPOSITORIO");
        this.authService.validateToken(token);
        return this.userService.addNewAlbum(albumRequest);
    }

}
