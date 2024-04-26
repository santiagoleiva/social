package ar.com.santiagoleiva.social.application.usecase;

import ar.com.santiagoleiva.social.domain.Tweet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;

public class CreateTweetUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateTweetUseCase.class);

    private final Clock clock;

    public CreateTweetUseCase(Clock clock) {
        this.clock = clock;
    }

    public Tweet execute(Long userId, String content) {
        throw new IllegalStateException("Not implemented yet");
    }

}
