package com.nashtech.rookie.yasa.util;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nashtech.rookie.yasa.entity.User;

import java.util.Date;
import java.util.UUID;

public class JWTService {

    private static final String SECRET = "KurgerBing";
    private static final String ISSUER = "KurgerBing";
    private static final String SUBJECT = "Users Details";

    private static Algorithm algorithm = Algorithm.HMAC256("KurgerBing");
    private static JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer("KurgerBing")
            .build();

    public static String createJWT(User user) {
        return JWT.create()
                .withSubject(SUBJECT)
                .withIssuer(ISSUER)
                .withClaim("username", user.getUsername())
                .withClaim("name", user.getName())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 5000L))
                .withJWTId(UUID.randomUUID()
                        .toString())
                .withNotBefore(new Date(System.currentTimeMillis() + 1000L))
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


//    public static void main(String[] args)
//    {
//        User user = new User();
//        user.setUsername("test jwt");
//        user.setName("nguyen");
//        System.out.println(verifyJWT(createJWT(user)));
//    }
}
