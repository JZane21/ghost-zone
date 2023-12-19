package com.ghostzone.user_service.infrastructure.utils;

import com.ghostzone.user_service.app.exception.UserServiceCustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatorUser {
    @Autowired
    private AuthenticationManager authenticationManager;

    public boolean isAuthenticated(String userName, String password){
        try{
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    userName,
                                    password
                            )
                    );
            return authenticate.isAuthenticated();
        }catch (Exception e){
            throw new UserServiceCustomException("Access Denided! Bad Request","401");
        }
    }
}
