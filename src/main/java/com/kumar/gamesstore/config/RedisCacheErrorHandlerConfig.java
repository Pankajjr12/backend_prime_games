package com.kumar.gamesstore.config;

import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisCacheErrorHandlerConfig {

    @Bean
    public SimpleCacheErrorHandler cacheErrorHandler() {
        return new SimpleCacheErrorHandler(); // Prevents 500 errors if Redis fails
    }
}
