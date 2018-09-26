package jwttokentest.demo.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import com.sun.net.httpserver.HttpExchange;
import jwttokentest.demo.test.JWTUtil;

import javax.servlet.*;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = servletRequest.getParameter("token");
        DecodedJWT jwt = JWT.decode(token);
        String username = jwt.getClaim("username").asString();
        HashMap<String,String> result = new HashMap<String,String>();
        boolean isAuth = false;
        if(username == null){
            result.put("code","401");
            result.put("msg","用户名不存在");
        }
        JWTUtil jwtUtil = new JWTUtil();
        if(jwtUtil.verify(token,username)){
            isAuth = true;
        }else {
            result.put("code","401");
            result.put("msg","请重新登陆");
        }
        if(!isAuth){
            PrintWriter writer = null;
            OutputStreamWriter osw = null;
            try {
                osw = new OutputStreamWriter(servletResponse.getOutputStream(),
                        "UTF-8");
                writer = new PrintWriter(osw, true);
                String jsonStr = result.toString();
                writer.write(jsonStr);
                writer.flush();
                writer.close();
                osw.close();
            } catch (UnsupportedEncodingException e) {
                System.out.println("过滤器返回信息失败:" + e.getMessage());
            } catch (IOException e) {
                System.out.println("过滤器返回信息失败:" + e.getMessage());
            } finally {
                if (null != writer) {
                    writer.close();
                }
                if (null != osw) {
                    osw.close();
                }
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
    @Override
    public void destroy() {

    }
}
