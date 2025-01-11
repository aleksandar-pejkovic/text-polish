package task.text_polish.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;

@Configuration
public class FeignClientConfiguration {

    private static final int MAX_WAIT_TIME_IN_MILLISECONDS = 500;
    private static final int REFRESH_PERIOD_IN_SECONDS = 1;
    private static final int MAX_ATTEMPTS = 10;

    @Bean
    public RateLimiter rateLimiter() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .timeoutDuration(Duration.ofMillis(MAX_WAIT_TIME_IN_MILLISECONDS))
                .limitRefreshPeriod(Duration.ofSeconds(REFRESH_PERIOD_IN_SECONDS))
                .limitForPeriod(MAX_ATTEMPTS)
                .build();

        return RateLimiterRegistry.of(config).rateLimiter("proofreading-service");
    }
}
