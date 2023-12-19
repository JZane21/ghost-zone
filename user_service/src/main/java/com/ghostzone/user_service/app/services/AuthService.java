package com.ghostzone.user_service.app.services;

import com.ghostzone.user_service.app.dtos.CreateUserDTO;
import com.ghostzone.user_service.app.exception.UserServiceCustomException;
import lombok.extern.log4j.Log4j2;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public CreateUserDTO encryptUserPassword(CreateUserDTO createUserDTO){
        String password = createUserDTO.getPassword();
        createUserDTO.setPassword(encryptPassword(password));
        return createUserDTO;
    }

    public String encryptPassword(String password){
        return passwordEncoder.encode(password);
    }

    public String decryptUserPassword(String encryptPassword){
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(jwtService.getSecret());
        try {
            return textEncryptor.decrypt(encryptPassword);
        } catch (Exception e) {
            throw new UserServiceCustomException("Unauthorized access, password or email incorrect","403");
        }
    }

    public String generateToken(String username){
        String token = jwtService.generateToken(username);
        return token;
    }

    public void validateToken(String token){
        jwtService.validateToken(token);
    }
}
