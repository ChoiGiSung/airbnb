package com.codesquad.coco.domain.oauth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerKey {

    private String jwtServerKey;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public ServerKey(String key) {
        this.jwtServerKey = key;
        logger.debug(jwtServerKey);
    }

    public String getJwtServerKey() {
        return jwtServerKey;
    }
}
