package ar.com.santiagoleiva.social.infrastructure.configuration;

import ar.com.santiagoleiva.social.application.usecase.CreateTweetUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class UseCaseProvider {

    @Bean
    public CreateTweetUseCase createTweetUseCase(Clock clock) {
        return new CreateTweetUseCase(clock);
    }

}
