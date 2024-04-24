package ar.com.santiagoleiva.social.infrastructure.mock;

import ar.com.santiagoleiva.social.application.port.CreateTweetPort;
import ar.com.santiagoleiva.social.domain.Tweet;
import ar.com.santiagoleiva.social.domain.User;

import java.time.LocalDate;

public class MockCreateTweetPort implements CreateTweetPort {

    @Override
    public Tweet create(String content, User user) {
        return new Tweet(1L, content, user, LocalDate.now());
    }

}
