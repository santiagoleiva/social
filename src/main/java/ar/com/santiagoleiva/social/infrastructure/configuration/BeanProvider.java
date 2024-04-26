package ar.com.santiagoleiva.social.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class BeanProvider {

    @Bean
    @ConditionalOnMissingBean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    public TweetsConfiguration tweetsConfiguration(@Value("${tweets.max-length}") int maxLength) {
        return new TweetsConfiguration(maxLength);
    }

    public record TweetsConfiguration(int maxLength) {
    }

}
