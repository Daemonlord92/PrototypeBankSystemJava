package org.banksystem.BankSystem.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration("jwtConfigProps")
@ConfigurationProperties(prefix = "jwt")
public class JwtConfigProperty {
    private String secret;
    private long expiration;
}
