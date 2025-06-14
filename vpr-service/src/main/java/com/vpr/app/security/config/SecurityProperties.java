package com.vpr.app.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Configuration properties for security-related settings.
 * Loads values from security-config.yml
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "security.whitelist")
public class SecurityProperties {
    private List<String> urls;
} 