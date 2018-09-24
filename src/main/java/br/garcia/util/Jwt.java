package br.garcia.util;

import br.garcia.configuration.Props;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class Jwt {

    private static final String SECRET_KEY = Props.applicationProperties.getProperty("jwt_key");

    public static String create(String id){
        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

            return JWT.create()
                    .withIssuer(id)
                    .sign(algorithm);
        }
        catch (JWTCreationException ex){
            return "";
        }
    }

    public static boolean verify(String token, String id){
        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(id)
                    .build();
            DecodedJWT jwt = verifier.verify(token);

            // o uuid é o dono do token?
            return jwt.getIssuer().equals(id);
        }
        catch (JWTVerificationException ex){
            return false;
        }
    }
}


