package ar.com.santiagoleiva.social.infrastructure.configuration;

import ar.com.santiagoleiva.social.infrastructure.jdbc.TweetJdbcCrudRepository;
import ar.com.santiagoleiva.social.infrastructure.jdbc.TweetJdbcRepository;
import ar.com.santiagoleiva.social.infrastructure.jdbc.UserJdbcCrudRepository;
import ar.com.santiagoleiva.social.infrastructure.jdbc.UserJdbcRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortProvider {

    @Bean // FindUserPort, UserFollowPort
    public UserJdbcRepository userJdbcRepository(UserJdbcCrudRepository userJdbcCrudRepository) {
        return new UserJdbcRepository(userJdbcCrudRepository);
    }

    @Bean // CreateTweetPort, FindTweetPort
    public TweetJdbcRepository tweetJdbcRepository(
            TweetJdbcCrudRepository tweetJdbcCrudRepository,
            UserJdbcCrudRepository userJdbcCrudRepository
    ) {
        return new TweetJdbcRepository(tweetJdbcCrudRepository, userJdbcCrudRepository);
    }

}
