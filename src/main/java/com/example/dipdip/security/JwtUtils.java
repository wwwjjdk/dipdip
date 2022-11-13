package com.example.dipdip.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class JwtUtils {
    @Value("$(jwt.token.secret)")
    private String secret;
    //@Value("$(jwt.token.expired)")
    //private Long valid;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        String commaSeparatedListOfAuthorities=  userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        claims.put("authorities", commaSeparatedListOfAuthorities);
        return createToken(claims, userDetails.getUsername());
    }
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractAuthorities(String token) {
        Function<Claims, String> claimsListFunction = claims -> (String)claims.get("authorities");
        return extractClaim(token, claimsListFunction);
    }

    private  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expireTimeFromNow())
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }
    private Date expireTimeFromNow() {
        return new Date(System.currentTimeMillis()+900000);
    }
}
