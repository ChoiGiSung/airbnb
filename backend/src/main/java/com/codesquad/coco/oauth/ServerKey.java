package com.codesquad.coco.oauth;

import com.codesquad.coco.utils.GetPropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ServerKey {

    private static final String JWT_PATH = "jwt.server.key";
    private String jwtServerKey;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public ServerKey() {
        this.jwtServerKey = GetPropertyUtil.getProperty(JWT_PATH);
        logger.debug(jwtServerKey);
    }

    public String getJwtServerKey() {
        return jwtServerKey;
    }
}
