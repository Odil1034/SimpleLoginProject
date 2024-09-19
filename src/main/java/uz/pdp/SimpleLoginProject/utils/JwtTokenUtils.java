package uz.pdp.SimpleLoginProject.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtils {

    String SECRET_KEY = "02e40a241239569781c87f5b727cbbbc7eede049967df1eb6bf38f253f1b3bda";
    private static final Long EXPIRED_DATE = System.currentTimeMillis() + 60 * 10 * 1000;

    private SecretKey getKeysHs256() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }


    public String generateToken(String username){
        return createToken(username,new HashMap<>());
    }
    public String generateToken(String username, Authentication authenticate){
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("roles",authenticate.getAuthorities());
        return createToken(username,claims);
    }

    public String getSubject(String token) {
        Claims jwtBody = getJwtBody(token);
        return jwtBody.getSubject();
    }

    public String createToken(String subject, Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(getKeysHs256(), SignatureAlgorithm.HS256)
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(EXPIRED_DATE))
                .compact();
    }

    private Claims getJwtBody(String token) {
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(getKeysHs256())
                .build();
        return (Claims) parser
                .parse(token)
                .getBody();
    }

    public Date getExpiration(String token) {
        Claims jwtBody = getJwtBody(token);
        return jwtBody.getExpiration();
    }
}
