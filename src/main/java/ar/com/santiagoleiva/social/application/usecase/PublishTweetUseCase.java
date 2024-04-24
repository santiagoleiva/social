package ar.com.santiagoleiva.social.application.usecase;

import ar.com.santiagoleiva.social.application.port.CreateTweetPort;
import ar.com.santiagoleiva.social.application.port.FindUserPort;
import ar.com.santiagoleiva.social.domain.Tweet;
import ar.com.santiagoleiva.social.domain.User;
import ar.com.santiagoleiva.social.infrastructure.ConfigurationProvider.TweetsConfiguration;

public class PublishTweetUseCase {

    private final TweetsConfiguration tweetsConfiguration;
    private final CreateTweetPort createTweetPort;
    private final FindUserPort findUserPort;

    public PublishTweetUseCase(
            TweetsConfiguration tweetsConfiguration,
            CreateTweetPort createTweetPort,
            FindUserPort findUserPort
    ) {
        this.createTweetPort = createTweetPort;
        this.findUserPort = findUserPort;
        this.tweetsConfiguration = tweetsConfiguration;
    }

    public Tweet execute(Long userId, String content) {
        if (isNotValid(content)) {
            throw new IllegalArgumentException();
        }

        return createTweetPort.create(content, findUserById(userId));
    }

    private boolean isNotValid(String content) {
        return content.length() > tweetsConfiguration.maxLength();
    }

    private User findUserById(Long userId) {
        return findUserPort.byId(userId).orElseThrow();
    }

}
