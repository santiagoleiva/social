package ar.com.santiagoleiva.social.application.usecase;

import ar.com.santiagoleiva.social.application.port.CreateTweetPort;
import ar.com.santiagoleiva.social.application.port.FindUserPort;
import ar.com.santiagoleiva.social.domain.Tweet;
import ar.com.santiagoleiva.social.domain.User;
import ar.com.santiagoleiva.social.domain.exception.InvalidException;
import ar.com.santiagoleiva.social.domain.exception.NonProcessableException;
import ar.com.santiagoleiva.social.domain.exception.NotFoundException;
import ar.com.santiagoleiva.social.infrastructure.configuration.BeanProvider.TweetsConfiguration;

import java.time.Clock;
import java.time.LocalDateTime;

import static java.util.Objects.isNull;

public class CreateTweetUseCase {

    private static final String INVALID_CONTENT_MESSAGE = "Content must not be null";
    private static final String INVALID_CONTENT_LENGTH_MESSAGE_FORMAT = "The character limit has been exceeded (%d)";
    private static final String USER_NOT_FOUND_MESSAGE_FORMAT = "User '%d' not found";

    private final Clock clock;
    private final TweetsConfiguration tweetsConfiguration;
    private final CreateTweetPort createTweetPort;
    private final FindUserPort findUserPort;

    public CreateTweetUseCase(
            Clock clock,
            TweetsConfiguration tweetsConfiguration,
            CreateTweetPort createTweetPort,
            FindUserPort findUserPort
    ) {
        this.clock = clock;
        this.tweetsConfiguration = tweetsConfiguration;
        this.createTweetPort = createTweetPort;
        this.findUserPort = findUserPort;
    }

    public Tweet execute(Long userId, String content) {
        doValidations(content);
        final User user = getUserById(userId);
        return createTweet(content, user);
    }

    private void doValidations(String content) {
        if (isNull(content)) {
            throw new InvalidException(INVALID_CONTENT_MESSAGE);
        }

        if (content.length() > tweetsConfiguration.maxLength()) {
            throw new NonProcessableException(INVALID_CONTENT_LENGTH_MESSAGE_FORMAT.formatted(tweetsConfiguration.maxLength()));
        }
    }

    private User getUserById(Long userId) {
        return findUserPort.byId(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_MESSAGE_FORMAT.formatted(userId)));
    }

    private Tweet createTweet(String content, User user) {
        return createTweetPort.create(content, user, LocalDateTime.now(clock));
    }

}
