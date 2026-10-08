package ru.learn.startuplogger;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(StartupLoggerProperties.class)
@ConditionalOnBooleanProperty(prefix = "custom.startup-logger", name = "enabled", matchIfMissing = true)
public class StartupLoggerAutoConfiguration {

    @Bean
    StartupLoggingListener startupLoggingListener() {
        return new StartupLoggingListener();
    }
}
