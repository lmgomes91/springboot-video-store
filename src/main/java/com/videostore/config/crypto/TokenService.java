package com.videostore.config.crypto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import com.videostore.modules.users.entities.User;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    public String generateToken(Authentication authenticate){
        User user = (User) authenticate.getPrincipal();
        Date date = new Date();
        Date expirationDate = new Date(date.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API Video Store")
                .setSubject(user.getId().toString())
                .setIssuedAt(date)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    public boolean isValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public String getUserId(String token) {
        Claims claim = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return String.valueOf(claim.getSubject());
    }
}
