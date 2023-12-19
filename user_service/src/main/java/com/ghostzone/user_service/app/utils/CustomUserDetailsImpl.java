package com.ghostzone.user_service.app.utils;

import com.ghostzone.user_service.app.exception.UserServiceCustomException;
import com.ghostzone.user_service.domain.entity.UserEntity;
import com.ghostzone.user_service.domain.interfaces.CustomUserDetails;
import com.ghostzone.user_service.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> credential = Optional.ofNullable(userRepository.findByUsername(username));
        return credential.map(CustomUserDetails::new).orElseThrow(
                () -> new UserServiceCustomException("User not found","404")
        );
    }
}
