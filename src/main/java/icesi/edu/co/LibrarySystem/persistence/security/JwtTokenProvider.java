package icesi.edu.co.LibrarySystem.persistence.security;

import com.google.auth.oauth2.JwtClaims;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.security.KeyStore;
import java.util.Date;
import java.util.List;

import com.nimbusds.jwt.JWTClaimsSet;

public class JwtTokenProvider {

    private final KeyStore keyStore;

    public JwtTokenProvider(KeyStore keyStore) {
        this.keyStore = keyStore;
    }

    public String generateToken(String username, List<String> authorities) throws Exception {
        // Create JWT claims
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .issuer("Bookstore API")
                .audience("Bookstore App")
                .claim("exp", new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .subject(username)
                .claim("authorities", authorities)
                .build();

        // Create JWT signing key
        Key key = keyStore.getKey("jwt", "password".toCharArray());

        // Sign the JWT
        JwtBuilder jwtBuilder = Jwts.builder().signWith(SignatureAlgorithm.RS256, key);

        // Create and return the token
        return jwtBuilder.compact();
    }
}




