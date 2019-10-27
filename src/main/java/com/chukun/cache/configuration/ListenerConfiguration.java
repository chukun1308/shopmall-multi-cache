package com.chukun.cache.configuration;

import com.chukun.cache.listener.CacheInitialListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenerConfiguration {

    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
        ServletListenerRegistrationBean cacheListener = new ServletListenerRegistrationBean();
        cacheListener.setListener(new CacheInitialListener());
        return cacheListener;
    }
}
