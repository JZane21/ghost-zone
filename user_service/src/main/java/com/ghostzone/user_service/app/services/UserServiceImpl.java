package com.ghostzone.user_service.app.services;

import com.ghostzone.user_service.app.dtos.CreateUserDTO;
import com.ghostzone.user_service.app.dtos.UpdateUserDTO;
import com.ghostzone.user_service.app.dtos.UserDTO;
import com.ghostzone.user_service.app.exception.UserServiceCustomException;
import com.ghostzone.user_service.domain.interfaces.UserService;
import com.ghostzone.user_service.domain.entity.UserEntity;
import com.ghostzone.user_service.infrastructure.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;
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
                .admin(newUserDTO.isAdmin())
                .artist(newUserDTO.isArtist())
                .description(newUserDTO.getDescription())
                .build();
        userRepository.save(userEntity);
        log.info("User Created");

        return UserDTO.builder()
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .admin(userEntity.isAdmin())
                .artist(userEntity.isArtist())
                .description(userEntity.getDescription())
                .build();
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        log.info(announcement + "Getting user with email " + email);
        UserEntity userEntity = userRepository.findByEmail(email);
        log.info("User gotten");

        return UserDTO.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .admin(userEntity.isAdmin())
                .artist(userEntity.isArtist())
                .description(userEntity.getDescription())
                .build();
    }

    @Override
    public UserDTO updateInfoUserByEmail(String email, UpdateUserDTO updateUserDTO) {
        log.info(announcement + "Updating user with email " + email);
        UserEntity userEntity = userRepository.findByEmail(email);
        userEntity.setUsername(
                updateUserDTO.getUsername() != null ? updateUserDTO.getUsername()
                        : userEntity.getUsername()
        );
        userEntity.setArtist(
                userEntity.isArtist() || updateUserDTO.isArtist());
        userEntity.setAdmin(userEntity.isAdmin() || updateUserDTO.isAdmin());
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
                .admin(userEntity.isAdmin())
                .artist(userEntity.isArtist())
                .description(userEntity.getDescription())
                .build();
    }

    @Override
    public void deleteUserByEmail(String email) {
        log.info(announcement + "Deleting user with email " + email);
        UserEntity userEntity = userRepository.findByEmail(email);
        userRepository.delete(userEntity);
        log.info("User Deleted");
    }
}
