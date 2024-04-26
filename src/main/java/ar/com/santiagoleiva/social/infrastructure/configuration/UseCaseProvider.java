package ar.com.santiagoleiva.social.infrastructure.configuration;

import ar.com.santiagoleiva.social.application.port.CreateTweetPort;
import ar.com.santiagoleiva.social.application.port.FindUserPort;
import ar.com.santiagoleiva.social.application.usecase.CreateTweetUseCase;
import ar.com.santiagoleiva.social.infrastructure.configuration.BeanProvider.TweetsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class UseCaseProvider {

    @Bean
    public CreateTweetUseCase createTweetUseCase(
            Clock clock,
            TweetsConfiguration tweetsConfiguration,
            CreateTweetPort createTweetPort,
            FindUserPort findUserPort
    ) {
        return new CreateTweetUseCase(clock, tweetsConfiguration, createTweetPort, findUserPort);
    }

}
