/*package org.mjulikelion.likelion12th3weekhomework.authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class JwtEncoder {
    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String encodeJwtBearerToken(final String payload) {
        return Jwts.builder()
                .setSubject(payload)
                .signWith(key)
                .compact();
    }

    public String decodeJwtBearerToken() {

    }
}
*/