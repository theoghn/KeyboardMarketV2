package com.tfluke.KBDMarket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain defaultFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
//        authorize any request for the user path
//        ask for auth for any admin path
        httpSecurity.authorizeHttpRequests(
                (authz) -> authz
                        .requestMatchers("/api/*/admin/**").authenticated()
                        .requestMatchers("/api/*/user/**").permitAll()


        );
        httpSecurity.httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
