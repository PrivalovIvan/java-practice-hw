package ru.learn.startuplogger;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;

public class StartupLoggingListener implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger log = LoggerFactory.getLogger(StartupLoggingListener.class);

    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        Environment env = event.getApplicationContext().getEnvironment();
        String[] profiles = env.getActiveProfiles();
        String activeProfile = profiles.length > 0 ? String.join(", ", profiles) : "default";
        String appVersion = env.getProperty("spring.application.version", "unknown");
        String dbUrl = env.getProperty("spring.datasource.url", "Н/Д");
        assert event.getTimeTaken() != null;
        long startupTime = event.getTimeTaken().toMillis();

        log.info("====== [ STARTUP INFO ] ======");
        log.info("Версия приложения: {}", appVersion);
        log.info("Активный профиль: {}", activeProfile);
        log.info("Подключение к БД: {}", dbUrl);
        log.info("Время старта контекста: {} мс", startupTime);
        log.info("==============================");
    }
}
