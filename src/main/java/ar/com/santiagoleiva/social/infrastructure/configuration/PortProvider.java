package ar.com.santiagoleiva.social.infrastructure.configuration;

import ar.com.santiagoleiva.social.application.port.CreateTweetPort;
import ar.com.santiagoleiva.social.application.port.FindUserPort;
import ar.com.santiagoleiva.social.application.port.FollowUserPort;
import ar.com.santiagoleiva.social.infrastructure.jdbc.TweetJdbcCrudRepository;
import ar.com.santiagoleiva.social.infrastructure.jdbc.TweetJdbcRepository;
import ar.com.santiagoleiva.social.infrastructure.jdbc.UserJdbcCrudRepository;
import ar.com.santiagoleiva.social.infrastructure.jdbc.UserJdbcRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortProvider {

    @Bean
    public UserJdbcRepository userJdbcRepository(UserJdbcCrudRepository userJdbcCrudRepository) {
        return new UserJdbcRepository(userJdbcCrudRepository);
    }

    @Bean
    public TweetJdbcRepository tweetJdbcRepository(TweetJdbcCrudRepository tweetJdbcCrudRepository) {
        return new TweetJdbcRepository(tweetJdbcCrudRepository);
    }

    @Bean
    public FindUserPort findUserPort(UserJdbcCrudRepository userJdbcCrudRepository) {
        return userJdbcRepository(userJdbcCrudRepository);
    }

    @Bean
    public FollowUserPort followUserPort(UserJdbcCrudRepository userJdbcCrudRepository) {
        return userJdbcRepository(userJdbcCrudRepository);
    }

    @Bean
    public CreateTweetPort createTweetPort(TweetJdbcCrudRepository tweetJdbcCrudRepository) {
        return tweetJdbcRepository(tweetJdbcCrudRepository);
    }


}
