package com.codesquad.coco.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/secretKey.properties")
public class ApplicationContextServe implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
        logger.debug("6-1");
        logger.debug(context.toString());
        logger.debug(applicationContext.toString());
    }

}
