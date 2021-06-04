package com.codesquad.coco.oauth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerKey {

    private String jwtServerKey;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public ServerKey(String jwt) {
        this.jwtServerKey = jwt;
        logger.debug(jwtServerKey);
    }

    public String getJwtServerKey() {
        return jwtServerKey;
    }
}
