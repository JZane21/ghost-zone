package com.ghostzone.user_service.domain.interfaces;

import com.ghostzone.user_service.app.dtos.CreateUserDTO;
import com.ghostzone.user_service.app.dtos.UpdateUserDTO;
import com.ghostzone.user_service.app.dtos.UserDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    UserDTO postUser(CreateUserDTO createUserDTO);
    UserDTO getUserById(Long id);
    UserDTO updateInfoUserById(Long id, UpdateUserDTO updateUserDTO);
    void deleteUserById(Long id);

    ResponseEntity<SongGetByIdResponse> getMySongById(long id);

    void deleteMySongById(long id);

    ResponseEntity<Long> addMyNewSong(SongRequest songRequest);

    ResponseEntity<AlbumGetByIdResponse> getMyAlbumById(long id);

    void deleteAlbumById(long id);

    ResponseEntity<Long> addNewAlbum(AlbumRequest albumRequest);

}
