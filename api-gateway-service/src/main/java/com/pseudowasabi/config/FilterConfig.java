package com.pseudowasabi.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {

//    @Bean
    public
    RouteLocator customGatewayRoutes(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(r -> r.path("/first-service/**")
                        .filters(f -> f.addRequestHeader("first-req-header", "first-req-header-value")
                                .addResponseHeader("first-resp-header", "first-resp-header-value"))
                        .uri("http://127.0.0.1:8081"))
                .route(r -> r.path("/second-service/**")
                        .filters(f -> f.addRequestHeader("second-req-header", "second-req-header-value")
                                .addResponseHeader("second-resp-header", "second-resp-header-value"))
                        .uri("http://127.0.0.1:8082"))
                .build();
    }
}
