package ru.learn.javapracticehw.security.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.jwt")
public record SecurityProps(
    String secretKey,
    long expiration
) {
}
