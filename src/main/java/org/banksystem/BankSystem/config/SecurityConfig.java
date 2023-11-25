package org.banksystem.BankSystem.config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests()
                .requestMatchers("/auth/**", "/bank/")
                .permitAll()
                .requestMatchers("/bank/apply")
                .hasAnyAuthority("ROLE_CLIENT", "ROLE_TELLER", "ROLE_MANAGER", "ROLE_ADMIN")
                .requestMatchers("/bank/", "/bank/terminate")
                .hasAnyAuthority("ROLE_MANAGER", "ROLE_ADMIN")
                .requestMatchers("/transactions/userTransactions/**", "/transactions/**", "/transactions/transaction/**")
                .hasAuthority("ROLE_CLIENT")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
