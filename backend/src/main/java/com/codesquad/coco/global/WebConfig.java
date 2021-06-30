package com.codesquad.coco.global;

import com.codesquad.coco.global.auth.AuthInterceptor;
import com.codesquad.coco.global.auth.GitHubDeviceTypeConvertor;
import com.codesquad.coco.global.auth.UserIdHandlerMethodResolver;
import com.codesquad.coco.domain.oauth.ServerKey;
import com.codesquad.coco.utils.PropertyUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@PropertySource("classpath:/secretKey.properties")
public class WebConfig implements WebMvcConfigurer {

    private static final String JWT_PATH = "jwt.server.key";

    @Bean
    public ServerKey serverKey() {
        return new ServerKey(PropertyUtil.getProperty(JWT_PATH));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor(serverKey()))
                .addPathPatterns("/rooms/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UserIdHandlerMethodResolver(serverKey()));
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new GitHubDeviceTypeConvertor());
    }
}
