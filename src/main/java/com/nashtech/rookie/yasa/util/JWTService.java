package com.nashtech.rookie.yasa.util;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nashtech.rookie.yasa.entity.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Component
public class JWTService {

    private static final String SECRET = "KurgerBing";
    private static final String ISSUER = "KurgerBing";
    private static final String SUBJECT = "Users Details";

    private static final int expireInMs = 60 * 1000;

    private static Algorithm algorithm = Algorithm.HMAC256(SECRET);
    private static JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer(ISSUER)
            .build();


    public static String createJWT(User user) {
        return JWT.create()
                .withSubject(SUBJECT)
                .withIssuer(ISSUER)
                .withClaim("username", user.getUsername())
                .withClaim("name", user.getName())
                .withClaim("role", user.getRole())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expireInMs))
                .withJWTId(UUID.randomUUID()
                        .toString())
                .sign(algorithm);
    }

    private static DecodedJWT verifyJWT(String jwtToken) {
        try {
            return verifier.verify(jwtToken);
        } catch (JWTVerificationException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    private static boolean isJWTExpired(DecodedJWT decodedJWT) {
        Date expiresAt = decodedJWT.getExpiresAt();
        return expiresAt.before(new Date());
    }

    private static DecodedJWT decodedJWT(String jwtToken) {
        try {
            return JWT.decode(jwtToken);
        } catch (JWTDecodeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static boolean validate(String token) {
        if (getUsername(token) != null && isExpired(token)) {
            return true;
        }
        return false;
    }

    private static String getClaims(String token) {
        var claim = decodedJWT(token).getClaim("username");
        return  claim.asString();
    }

    public static boolean isExpired(String token) {
         return decodedJWT(token).getExpiresAt().after(new Date(System.currentTimeMillis()));
    }

    public static String getUsername(String token) {
        return decodedJWT(token).getClaim("username").asString();
    }
    public static String getRole(String token) {return decodedJWT(token).getClaim("role").asString();}

    public static void main(String[] args)
    {
        User user = new User();
        user.setUsername("test jwt");
        user.setName("nguyen");
        System.out.println(getUsername(createJWT(user)));
    }
}
