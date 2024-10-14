package com.nhnacademy.dooraygateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LogginGlobalFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Incoming request: {} {}", exchange.getRequest().getMethod(), exchange.getRequest().getURI());
        log.info("Headers: {}", exchange.getRequest().getHeaders());
        // Post 로깅 (응답 후 로깅)
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("Response status: {}", exchange.getResponse().getStatusCode());
        }));
    }
}
