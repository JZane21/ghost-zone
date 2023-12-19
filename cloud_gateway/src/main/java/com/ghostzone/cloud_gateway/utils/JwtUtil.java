package com.ghostzone.cloud_gateway.utils;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUtil {
    private static final String SECRET = "f583e8599153dccb5acb283254c2fa32dc699740e76910c959cc578097b37f6d";

    public void validateToken(final String token){
        Jwts.parserBuilder().setSigningKey(getSignKey())
                .build().parseClaimsJws(token);
    }

    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
