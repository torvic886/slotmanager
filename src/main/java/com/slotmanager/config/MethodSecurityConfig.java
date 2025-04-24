package com.slotmanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity // Esto habilita las anotaciones @PreAuthorize
public class MethodSecurityConfig {
}
