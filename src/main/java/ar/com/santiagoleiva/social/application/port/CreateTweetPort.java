package ar.com.santiagoleiva.social.application.port;

import ar.com.santiagoleiva.social.domain.Tweet;
import ar.com.santiagoleiva.social.domain.User;

import java.time.LocalDateTime;

public interface CreateTweetPort {

    Tweet create(String content, User user, LocalDateTime createdAt);

}
