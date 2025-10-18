package com.example.APIGateway.filter;

import com.example.APIGateway.config.RoleMappingProperties;
import com.example.APIGateway.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class JwtAuthFilter implements GlobalFilter, Ordered {
    private final JwtService jwtService;
    private final RoleMappingProperties roleMappingProperties;

    @Autowired
    public JwtAuthFilter(JwtService jwtService, RoleMappingProperties roleMappingProperties) {
        this.jwtService = jwtService;
        this.roleMappingProperties = roleMappingProperties;
    }

    private List<String> getRoles(String path) {
        for (RoleMappingProperties.RouteRole role: roleMappingProperties.getRoles()) {
            List<String> listRolePath = Arrays.stream(role.getPath().split(",")).map(String::trim).toList();
            if (listRolePath.contains(path)) {
                return role.getRole();
            }
        }
        return new ArrayList<>();
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        // Cho phép qua nếu là Auth endpoint
        if (path.contains("/auth/")) {
            return chain.filter(exchange);
        }

        // Lấy token từ header
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // Cắt token và kiểm tra tính hợp lệ
        String token = authHeader.substring(7);
        boolean roleHasNoPermission = getRoles(path).stream().noneMatch(jwtService.extractRoles(token)::contains);
        if (!jwtService.validateToken(token)
                || roleHasNoPermission) {
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }
        // Nếu token hợp lệ → cho phép đi tiếp
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}