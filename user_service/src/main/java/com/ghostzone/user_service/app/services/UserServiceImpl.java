package com.ghostzone.user_service.app.services;

import com.ghostzone.user_service.app.dtos.CreateUserDTO;
import com.ghostzone.user_service.app.dtos.UpdateUserDTO;
import com.ghostzone.user_service.app.dtos.UserDTO;
import com.ghostzone.user_service.app.exception.UserServiceCustomException;
import com.ghostzone.user_service.domain.interfaces.*;
import com.ghostzone.user_service.domain.entity.UserEntity;
import com.ghostzone.user_service.infrastructure.client.AlbumService;
import com.ghostzone.user_service.infrastructure.client.SongService;
import com.ghostzone.user_service.infrastructure.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private SongService songService;
    @Autowired
    private AlbumService albumService;

    private final String announcement = "User Service:";

    @Override
    public UserDTO postUser(CreateUserDTO createUserDTO) {
        if(userRepository.findByEmail(createUserDTO.getEmail()) != null
        || userRepository.findByUsername(createUserDTO.getUsername()) != null){
            throw new UserServiceCustomException("User already exist!","500");
        }

        log.info(announcement + "Creating user " + createUserDTO.getUsername());
        CreateUserDTO newUserDTO = authService.encryptUserPassword(createUserDTO);
        UserEntity userEntity = UserEntity.builder()
                .username(newUserDTO.getUsername())
                .password(newUserDTO.getPassword())
                .email(newUserDTO.getEmail())
                .description(newUserDTO.getDescription())
                .build();
        userRepository.save(userEntity);
        log.info("User Created");

        return UserDTO.builder()
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .description(userEntity.getDescription())
                .build();
    }

    @Override
    public UserDTO getUserById(Long id) {
        log.info(announcement + "Getting user with id " + id);
        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new UserServiceCustomException("User Error: Usuario no encontrado", "404")
        );
        log.info("User gotten");

        return UserDTO.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .description(userEntity.getDescription())
                .build();
    }

    @Override
    public UserDTO updateInfoUserById(Long id, UpdateUserDTO updateUserDTO) {
        log.info(announcement + "Updating user with id " + id);
        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new UserServiceCustomException("User Error: Usuario no encontrado", "404")
        );
        userEntity.setUsername(
                updateUserDTO.getUsername() != null ? updateUserDTO.getUsername()
                        : userEntity.getUsername()
        );
        userEntity.setDescription(
                updateUserDTO.getDescription() != null ? updateUserDTO.getDescription()
                        : userEntity.getDescription()
        );
        if(updateUserDTO.getPassword() != null){
            userEntity.setPassword(authService.encryptPassword(updateUserDTO.getPassword()));
        }
        userRepository.save(userEntity);
        log.info("User updated");
        return UserDTO.builder()
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .description(userEntity.getDescription())
                .build();
    }

    @Override
    public void deleteUserById(Long id) {
        log.info(announcement + "Deleting user with id " + id);
        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new UserServiceCustomException("User Error: Usuario no encontrado", "404")
        );
        userRepository.delete(userEntity);
        log.info("User Deleted");
    }

    @Override
    public ResponseEntity<SongGetByIdResponse> getMySongById(long id) {
        return this.songService.getSongById(id);
    }

    @Override
    public void deleteMySongById(long id) {
        this.songService.deleteSongById(id);
    }

    @Override
    public ResponseEntity<Long> addMyNewSong(SongRequest songRequest) {
        return this.songService.addSong(songRequest);
    }

    @Override
    public ResponseEntity<AlbumGetByIdResponse> getMyAlbumById(long id) {
        return this.albumService.getAlbumById(id);
    }

    @Override
    public void deleteAlbumById(long id) {
        this.albumService.deleteAlbum(id);
    }

    @Override
    public ResponseEntity<Long> addNewAlbum(AlbumRequest albumRequest) {
        return this.albumService.addAlbum(albumRequest);
    }
}
