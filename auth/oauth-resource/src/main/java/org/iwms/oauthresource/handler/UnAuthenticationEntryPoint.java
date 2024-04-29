package org.iwms.oauthresource.handler;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.iwms.oauthresource.utils.RespJson;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author leung
 */
@Slf4j
public class UnAuthenticationEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (authException instanceof InvalidBearerTokenException) {
            //log.info("Token 登录失效");
        }

        if (response.isCommitted()) {
            return;
        }

        // 401, 未认证
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // 返回响应信息
        ServletOutputStream outputStream = response.getOutputStream();

        RespJson fail = RespJson.error(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage() + "-UnAuthenticationEntryPoint-认证失败, uri-" + request.getRequestURI());
        outputStream.write(JSONUtil.toJsonStr(fail).getBytes(StandardCharsets.UTF_8));
        // 关闭流
        outputStream.flush();
        outputStream.close();
    }
}
