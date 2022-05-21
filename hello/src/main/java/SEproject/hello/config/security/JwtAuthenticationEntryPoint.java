package SEproject.hello.config.security;

import SEproject.hello.common.model.BaseResponse;
import SEproject.hello.config.jwt.TokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 유효한 자격증명을 제공하지 않고 접근하려 할 때 401
        BaseResponse baseResponse = new BaseResponse("메시지", 401);
        response.setStatus(401);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        if (TokenProvider.exception == 0) {
            baseResponse.setMsg("잘못된 JWT 서명입니다.");
        } else if (TokenProvider.exception == 1) {
            baseResponse.setMsg("만료된 JWT 토큰입니다.");
        } else if (TokenProvider.exception == 2) {
            baseResponse.setMsg("지원되지 않는 JWT 토큰입니다.");
        } else if (TokenProvider.exception == 3) {
            baseResponse.setMsg("JWT 토큰이 잘못되었습니다.");
        }
        response.getOutputStream().write(new ObjectMapper().writeValueAsString(baseResponse).getBytes());
    }
}
