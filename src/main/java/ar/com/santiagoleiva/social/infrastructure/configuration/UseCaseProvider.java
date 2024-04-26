package ar.com.santiagoleiva.social.infrastructure.configuration;

import ar.com.santiagoleiva.social.application.port.CreateTweetPort;
import ar.com.santiagoleiva.social.application.port.FindUserPort;
import ar.com.santiagoleiva.social.application.usecase.PublishTweetUseCase;
import ar.com.santiagoleiva.social.infrastructure.configuration.BeanProvider.TweetsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseProvider {

    @Bean
    public PublishTweetUseCase publishTweetUseCase(
            TweetsConfiguration tweetsConfiguration,
            CreateTweetPort createTweetPort,
            FindUserPort findUserPort
    ) {
        return new PublishTweetUseCase(tweetsConfiguration, createTweetPort, findUserPort);
    }

}
