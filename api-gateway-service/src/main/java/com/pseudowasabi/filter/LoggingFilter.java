package com.pseudowasabi.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {

    @Data
    public static class Config {
        // configuration properties
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }

    public LoggingFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
//        return ((exchange, chain) -> {
//            ServerHttpRequest request = exchange.getRequest();
//            ServerHttpResponse response = exchange.getResponse();
//
//            log.info("[LOGGING FILTER BASE MESSAGE] {}, {}", config.getBaseMessage(), request.getRemoteAddress());
//            if (config.isPreLogger()) {
//                log.info("[LOGGING PRE FILTER] Request ID: {}", request.getId());
//            }
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//                if (config.isPostLogger()) {
//                    log.info("[LOGGING POST FILTER] Response status code: {}", response.getStatusCode());
//                }
//            }));
//        });

        // using OrderedGatewayFilter to specify filter's order
        GatewayFilter filter = new OrderedGatewayFilter((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("[LOGGING FILTER BASE MESSAGE] {}, {}", config.getBaseMessage(), request.getRemoteAddress());
            if (config.isPreLogger()) {
                log.info("[LOGGING PRE FILTER] Request ID: {}", request.getId());
            }
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if (config.isPostLogger()) {
                    log.info("[LOGGING POST FILTER] Response status code: {}", response.getStatusCode());
                }
            }));
        }, Ordered.HIGHEST_PRECEDENCE);

        return filter;
    }
}