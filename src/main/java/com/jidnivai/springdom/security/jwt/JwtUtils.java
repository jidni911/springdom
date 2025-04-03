package com.jidnivai.springdom.security.jwt;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.jidnivai.springdom.security.services.UserDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

    @Value("${dom.app.jwtSecret}")
    private String jwtSecret;

    @Value("${dom.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${dom.app.jwtCookieName}")
    private String jwtCookie;

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
    }

    public ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal) {
        String jwt = generateTokenFromUsername(userPrincipal);
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/api").maxAge(24 * 60 * 60).httpOnly(true).build();
        return cookie;
    }



    public String generateTokenFromUsername(UserDetailsImpl user) {


    final Claims claims = Jwts.claims().setSubject(user.getUsername());
    List<String> roles = new ArrayList<>(user.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList()));
    claims.put("roles", roles);
    // List<String> roles = new ArrayList<>();
    // for (GrantedAuthority authority : user.getAuthorities()) {
    //   roles.add(authority.getAuthority());
    // }
    claims.put("roles", roles);
    claims.put("id", user.getUser().getId()!=null?user.getUser().getId():0);
    claims.put("user_name", user.getUsername()!=null?user.getUsername():"0");
//    claims.put("email", user.getEmail()!=null?user.getEmail():"");
    return Jwts.builder()
            .setClaims(claims)
            .setSubject(user.getUsername())
               .setIssuedAt(new Date())
               .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
               .signWith(key(), SignatureAlgorithm.HS256)
               .compact();
  }

  public String generateJwtToken(UserDetailsImpl userPrincipal) {
    return generateTokenFromUsername(userPrincipal);
  }

  public ResponseCookie getCleanJwtCookie() {
    ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/api").build();
    return cookie;
  }

}
