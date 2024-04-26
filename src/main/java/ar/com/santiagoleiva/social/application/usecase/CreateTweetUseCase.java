package ar.com.santiagoleiva.social.application.usecase;

import ar.com.santiagoleiva.social.application.port.CreateTweetPort;
import ar.com.santiagoleiva.social.application.port.FindUserPort;
import ar.com.santiagoleiva.social.domain.Tweet;
import ar.com.santiagoleiva.social.domain.User;
import ar.com.santiagoleiva.social.infrastructure.configuration.BeanProvider.TweetsConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.time.LocalDateTime;

public class CreateTweetUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateTweetUseCase.class);

    private final Clock clock;
    private final TweetsConfiguration tweetsConfiguration;
    private final CreateTweetPort createTweetPort;
    private final FindUserPort findUserPort;

    public CreateTweetUseCase(Clock clock, TweetsConfiguration tweetsConfiguration, CreateTweetPort createTweetPort, FindUserPort findUserPort) {
        this.clock = clock;
        this.tweetsConfiguration = tweetsConfiguration;
        this.createTweetPort = createTweetPort;
        this.findUserPort = findUserPort;
    }

    public Tweet execute(Long userId, String content) {
        validateContent(content);
        final User user = getUserById(userId);
        return createTweet(content, user);
    }

    private void validateContent(String content) {
        if (content.length() > tweetsConfiguration.maxLength()) {
            throw new IllegalArgumentException();
        }
    }

    private User getUserById(Long userId) {
        return findUserPort.byId(userId).orElseThrow();
    }

    private Tweet createTweet(String content, User user) {
        return createTweetPort.create(content, user, LocalDateTime.now(clock));
    }

}
