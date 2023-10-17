package com.tfluke.KBDMarket.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class UserManagementConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        var user = User.withUsername("theo")
                .password("{noop}123")
                .roles("read")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
