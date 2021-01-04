package com.ultimatefoodmanager.mediaservice.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtUtil {

    public static final String BEARER = "Bearer ";
    private static final String HEADER = "Authorization";
    private static final String SECRET = "***** ***";

    public static Claims validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(HEADER).replace(BEARER, "");
        return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
    }

    public static boolean checkJWTToken(HttpServletRequest request, HttpServletResponse res) {
        String authenticationHeader = request.getHeader(HEADER);
        return authenticationHeader != null && authenticationHeader.startsWith(BEARER);
    }
}
