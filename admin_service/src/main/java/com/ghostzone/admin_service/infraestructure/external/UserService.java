package com.ghostzone.admin_service.infraestructure.external;

import com.ghostzone.admin_service.domain.models.CreateUserDTO;
import com.ghostzone.admin_service.domain.models.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "USER-SERVICE/user")
public interface UserService {
    @PutMapping
    public UserDTO updateUser(@RequestParam("email") String email, @RequestBody CreateUserDTO createUserDto);

    @GetMapping
    public UserDTO getUser(@RequestParam("email") String email);
}
