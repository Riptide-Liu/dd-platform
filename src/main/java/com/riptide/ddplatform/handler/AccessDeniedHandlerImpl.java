package com.riptide.ddplatform.handler;

import com.alibaba.fastjson.JSON;
import com.riptide.ddplatform.enums.ApiEnum;
import com.riptide.ddplatform.util.ResultGenerator;
import com.riptide.ddplatform.util.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String json = JSON.toJSONString(ResultGenerator.genFailed(ApiEnum.ACCESS_DENIED));
        WebUtils.renderString(response,json);

    }
}
