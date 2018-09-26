package jwttokentest.demo.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import jwttokentest.demo.test.JWTUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class controller {
    @PostMapping("login")
    public String sign(String username,String passward){
        JWTUtil jwtUtil = new JWTUtil();
        if(jwtUtil.signin(username,passward)){
            return jwtUtil.sign(username);
        }else {
            return "no account";
        }
    }

    @GetMapping("token")
    public String auth(){
        return "go";
    }
}
