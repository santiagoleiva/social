package ar.com.santiagoleiva.social.infrastructure.mock;

import ar.com.santiagoleiva.social.application.port.CreateTweetPort;
import ar.com.santiagoleiva.social.domain.Tweet;
import ar.com.santiagoleiva.social.domain.User;

import java.time.LocalDateTime;

public class MockCreateTweetPort implements CreateTweetPort {

    @Override
    public Tweet create(String content, User user, LocalDateTime createdAt) {
        return new Tweet(1L, content, user, createdAt);
    }

}
