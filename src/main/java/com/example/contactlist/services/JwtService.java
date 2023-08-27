package com.example.contactlist.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtService {
    @Value("${jwt.secret}")
    private String jwtSecret;

    public String signToken(String subject, Map<String, ?> payload, Long expirationTime) {
        return Jwts.builder()
                .setClaims(payload)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(Objects.nonNull(expirationTime) ? new Date(expirationTime) : null)
                .signWith(signKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key signKey() {
        byte[] bytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(bytes);
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}