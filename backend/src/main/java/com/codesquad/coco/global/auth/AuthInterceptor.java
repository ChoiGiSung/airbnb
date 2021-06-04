package com.codesquad.coco.global.auth;

import com.codesquad.coco.global.exception.auth.NotLoggedInException;
import com.codesquad.coco.oauth.ServerKey;
import jwt.JWTUtils;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class AuthInterceptor implements HandlerInterceptor {

    private ServerKey serverKey;

    public AuthInterceptor(ServerKey serverKey) {
        this.serverKey = serverKey;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }

        Auth auth = ((HandlerMethod) handler).getMethodAnnotation(Auth.class);
        if (Objects.isNull(auth)) {
            return true;
        }
        String jwt = getJWT(request);
        JWTUtils.validateJWT(jwt, serverKey.getJwtServerKey());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    private String getJWT(HttpServletRequest request) {
        String jwt = request.getHeader(JWTUtils.HEADER_TYPE);
        if (Objects.isNull(jwt)) {
            throw new NotLoggedInException();
        }
        return jwt;
    }
}
