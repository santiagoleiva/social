package ar.com.santiagoleiva.social.application.usecase;

import ar.com.santiagoleiva.social.domain.Tweet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PublishTweetUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublishTweetUseCase.class);

    public Tweet execute(Long userId, String content) {
        throw new IllegalStateException("Not implemented yet");
    }

}
