package com.codesquad.coco.utils;


import com.codesquad.coco.global.ApplicationContextServe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:/secretKey.properties")
public class PropertyUtil {

    private static Logger logger = LoggerFactory.getLogger(PropertyUtil.class);

    private PropertyUtil() {
    }

    public static String getProperty(String propertyName) {
        return getProperty(propertyName, null);
    }

    public static String getProperty(String propertyName, String defaultValue) {
        String value = defaultValue;

        ApplicationContext applicationContext = ApplicationContextServe.getApplicationContext();
        if (applicationContext.getEnvironment().getProperty(propertyName) == null) {
            logger.debug(applicationContext.toString());
            logger.debug(propertyName);
        } else {
            value = applicationContext.getEnvironment().getProperty(propertyName).toString();
        }
        return value;

    }
}
