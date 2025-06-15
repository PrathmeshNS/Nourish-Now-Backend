package com.pscode.nourish_now.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {


    private String SECREATE_KEY = "";

    JwtService() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey key = keyGen.generateKey();
            this.SECREATE_KEY = Base64.getEncoder().encodeToString(key.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
    }

    public String generateJwtToken(String username, String role) {
        @SuppressWarnings("unused")
        Map<String, Object> claims = new HashMap<>();
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(role);
        return Jwts.builder()
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 360 * 120))
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        byte[] keyByte = Decoders.BASE64.decode(SECREATE_KEY);
        return Keys.hmacShaKeyFor(keyByte);
    }

    public String extractUsername(String token) throws JwtException {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) throws JwtException {

        final Claims claims = extractAllClaim(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaim(String token) throws JwtException {
        try {
            return Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (SignatureException e) {
            throw new JwtException("Invalid JWT signature");
        }
    }

    public Boolean verifyToken(String token, UserDetails userDetails) throws JwtException {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


//	public void setUpSpringAuthentication(Claims claims) {
//		@SuppressWarnings("unchecked")
//		List<String> authorities = (List<String>) claims.get("authorities");
//		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
//				authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
//		SecurityContextHolder.getContext().setAuthentication(auth);
//	}
}
