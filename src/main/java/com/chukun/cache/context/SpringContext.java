package com.chukun.cache.context;

import org.springframework.context.ApplicationContext;

/**
 * spring上下文
 */
public class SpringContext {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext){
        SpringContext.applicationContext = applicationContext;
    }
}
