package com.example.User.ManagementBackend.Service.JWT;

import com.example.User.ManagementBackend.Entity.User;
import com.example.User.ManagementBackend.Service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class JwtService {


    private final UserService userService;

    private final String jwtSecret = "ZmFrbGpka2Y7YWprZGZqYTtsZGZrYW";

    @Autowired
    public JwtService(UserService userService) {
        this.userService = userService;
    }

    public String generateToken(Authentication authentication,String email) {
        String username = authentication.getName();
        User user = userService.findByEmail(email);
        Date currentDate = new Date();
        Date expirationTime = new Date(currentDate.getTime() + 30 * 24 * 60 * 60 * 1000L);
        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return Jwts.builder()
                .setSubject(email)
                .claim("role", roles.get(0))
                .claim("username",username)
                .setIssuedAt(currentDate)
                .setExpiration(expirationTime)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Jwt token is expired or is invalid");
        }

    }

    public void expireToken(String token) {
        Date currentDate = new Date();
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        claims.setExpiration(new Date(currentDate.getTime()));
    }

    public String getJWTFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}