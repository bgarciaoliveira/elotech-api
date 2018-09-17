package br.garcia.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class Jwt {

    private static final String secretKey = "3gsiOM}GnWtX-w_~9`^?sYile]KTxs";

    public static String create(String id){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

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
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

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


