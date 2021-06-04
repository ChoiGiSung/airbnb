package com.codesquad.coco.global.auth;

import com.codesquad.coco.oauth.ServerKey;
import jwt.JWTUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class UserIdHandlerMethodResolver implements HandlerMethodArgumentResolver {

    private static final String WANT_INFO = "id";
    private ServerKey serverKey;

    public UserIdHandlerMethodResolver(ServerKey serverKey) {
        this.serverKey = serverKey;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        UserId userId = methodParameter.getParameterAnnotation(UserId.class);
        return userId != null;
    }

    @Override
    public Long resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String jwt = nativeWebRequest.getHeader(JWTUtils.HEADER_TYPE);
        Object infoFromJWT = JWTUtils.getInfoFromJWT(jwt, serverKey.getJwtServerKey(), WANT_INFO);
        return Long.valueOf(String.valueOf(infoFromJWT));
    }
}
