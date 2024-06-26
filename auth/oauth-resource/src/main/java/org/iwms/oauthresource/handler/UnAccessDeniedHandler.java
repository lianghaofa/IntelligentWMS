package org.iwms.oauthresource.handler;

import cn.hutool.json.JSONUtil;
import org.iwms.oauthresource.config.ResourceServerConfig;
import org.iwms.oauthresource.utils.RespJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author leung
 */
public class UnAccessDeniedHandler implements AccessDeniedHandler {

    static final Logger logger = LoggerFactory.getLogger(UnAccessDeniedHandler.class);
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        logger.info("403, 未授权, 禁止访问");
        // 403, 未授权, 禁止访问
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // 返回响应信息
        ServletOutputStream outputStream = response.getOutputStream();
        RespJson fail = RespJson.error(HttpServletResponse.SC_FORBIDDEN, "UnAccessDeniedHandler-未授权, 不允许访问, uri-".concat(request.getRequestURI()));
        outputStream.write(JSONUtil.toJsonStr(fail).getBytes(StandardCharsets.UTF_8));

        // 关闭流
        outputStream.flush();
        outputStream.close();
    }
}
