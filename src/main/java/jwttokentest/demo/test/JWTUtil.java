package jwttokentest.demo.test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JWTUtil {
    public boolean signin(String username,String password){
        if(username.equals("test") && password.equals("test")){
            return true;
        }else {
            return false;
        }
    }

    public String sign(String username){
        Date date = new Date(System.currentTimeMillis() + 1*60*1000);
        Algorithm algorithm = null;
        try {
            algorithm = Algorithm.HMAC256("test");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return JWT.create().withClaim("username",username).withExpiresAt(date).sign(algorithm);
    }

    public boolean verify(String token,String username){
        Algorithm algorithm = null;
        try {
            algorithm = Algorithm.HMAC256("test");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
