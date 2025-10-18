package com.example.APIGateway.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@ConfigurationProperties(prefix = "security")
@Configuration
public class RoleMappingProperties {
    private List<RouteRole> roles;

    @Data
    public static class RouteRole {
        private String path;
        private List<String> role;
    }
}
