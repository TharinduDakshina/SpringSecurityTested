package com.dakshina.SpringSecurity.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {
    private  final String secretKey;  // declare a instance of the crypto secretKey

    // generate secretKey use by crypto
    public JWTService() {
        try {
            SecretKey key = KeyGenerator.getInstance("HmacSHA256").generateKey();
            secretKey = Base64.getEncoder().encodeToString(key.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // generate the JWT token
    public String getJWTToken(String userName){

        Map<String,Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(userName)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .and()
                .signWith(getKey())
                .compact();


//        return Jwts.builder()
//                .subject("Dakshina")
//                .issuedAt(new Date(System.currentTimeMillis()))                     // issue time
//                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))  // expire time of this token
//                .signWith(getKey())
//                .compact();
    }

    private SecretKey getKey(){
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }

    // check token is valid and get the user data
    public  String getUSerName(String token){
//        try {
//            return Jwts
//                    .parser()
//                    .verifyWith(secretKey).build()
//                    .parseSignedClaims(token)
//                    .getPayload()
//                    .getSubject();
//        }catch (Exception e){
//            return "Invalid token.";
//        }

        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String userName =  getUSerName(token);
            return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
        }catch (Exception e){
            return false;
        }

    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token,Claims::getExpiration);
    }
}
