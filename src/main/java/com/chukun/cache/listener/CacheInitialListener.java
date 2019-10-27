package com.chukun.cache.listener;

import com.chukun.cache.constant.CommonConstant;
import com.chukun.cache.context.SpringContext;
import com.chukun.cache.kafka.CacheKafkaConsumer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CacheInitialListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(sc);
        SpringContext.setApplicationContext(context);
        new Thread(new CacheKafkaConsumer(CommonConstant.MESSAGE_CACHE_TOPIC)).start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
