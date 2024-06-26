package ar.com.santiagoleiva.social.infrastructure.configuration;

import ar.com.santiagoleiva.social.application.port.CreateTweetPort;
import ar.com.santiagoleiva.social.application.port.FindTweetPort;
import ar.com.santiagoleiva.social.application.port.FindUserPort;
import ar.com.santiagoleiva.social.application.port.UserFollowPort;
import ar.com.santiagoleiva.social.application.usecase.CreateTweetUseCase;
import ar.com.santiagoleiva.social.application.usecase.FollowUserUseCase;
import ar.com.santiagoleiva.social.application.usecase.GetUserTimelineUseCase;
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

    @Bean
    public FollowUserUseCase followUserUseCase(FindUserPort findUserPort, UserFollowPort userFollowPort) {
        return new FollowUserUseCase(findUserPort, userFollowPort);
    }

    @Bean
    public GetUserTimelineUseCase getUserTimelineUseCase(UserFollowPort userFollowPort, FindTweetPort findTweetPort) {
        return new GetUserTimelineUseCase(userFollowPort, findTweetPort);
    }

}
