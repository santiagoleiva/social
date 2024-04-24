package ar.com.santiagoleiva.social.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationProvider {

    @Bean
    public TweetsConfiguration tweetsConfiguration(@Value("${tweets.max-length}") int maxLength) {
        return new TweetsConfiguration(maxLength);
    }

    public record TweetsConfiguration(int maxLength) {
    }

}
