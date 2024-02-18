package com.example.chatmanagementservice.service.implementation;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629 ";

    public String extractToken(String token){
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }

//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }

    public UUID extractId(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("clientID", UUID.class);
    }

    public String extractEmail(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("email", String.class);
    }

    public String extractRole(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("role", String.class);
    }

    public boolean isTokenValidAndActive(String token){
        if(token == null)
            return false;

        if(isTokenExpired(token))
            return false;

        return true;

    }

    public boolean isTokenValidByAdmin(String token) {
        if(isTokenValidAndActive(token)) {
            String role = extractRole(token);
            return role.equals("ADMIN");
        }
        else {
            return false;
        }
    }

    public boolean isTokenValidByClient(String token) {
        if(isTokenValidAndActive(token)) {
            String role = extractRole(token);
            return role.equals("CLIENT");
        }
        else {
            return false;
        }
    }



    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

//    public boolean isTokenValid(String token, UserDetails userDetails){
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
//    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
