package com.nhnacademy.dooraygateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {
    @Bean
    public RouteLocator customLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("accounts-register", r -> r.path("/register").uri("http://localhost:8081/"))
                .route("accounts", r -> r.path("/accounts/**").uri("http://localhost:8081/"))
                .route("members", r->r.path("/members/**").uri("http://localhost:8082/"))
                .route("tasks", r -> r.path("/projects/**").uri("http://localhost:8082/")).build();
    }
}
