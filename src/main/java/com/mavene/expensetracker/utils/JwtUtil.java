package com.mavene.expensetracker.utils;

import com.mavene.expensetracker.constants.Contants;
import com.mavene.expensetracker.dto.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    public static Map<String, String> generateJWT(UserDto userDto) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts
                .builder()
                .signWith(SignatureAlgorithm.HS256, Contants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Contants.ACCESS_TOKEN_VALIDITY_MILLISECONDS))
                .claim("userId", userDto.getUserId())
                .claim("email", userDto.getEmail())
                .claim("firstName", userDto.getFirstName())
                .claim("lastName", userDto.getLastName())
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }
}
