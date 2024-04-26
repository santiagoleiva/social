package ar.com.santiagoleiva.social.infrastructure.jdbc;

import ar.com.santiagoleiva.social.application.port.CreateTweetPort;
import ar.com.santiagoleiva.social.domain.Tweet;
import ar.com.santiagoleiva.social.domain.User;
import ar.com.santiagoleiva.social.infrastructure.jdbc.model.TweetJdbcModel;
import ar.com.santiagoleiva.social.infrastructure.jdbc.model.UserJdbcModel;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.time.LocalDateTime;

public class TweetJdbcRepository implements CreateTweetPort {

    private final TweetJdbcCrudRepository tweetJdbcCrudRepository;

    public TweetJdbcRepository(TweetJdbcCrudRepository tweetJdbcCrudRepository) {
        this.tweetJdbcCrudRepository = tweetJdbcCrudRepository;
    }

    @Override
    public Tweet create(String content, User user, LocalDateTime createdAt) {
        final AggregateReference<UserJdbcModel, Long> userReference = AggregateReference.to(user.id());
        final TweetJdbcModel toCreate = new TweetJdbcModel(null, content, createdAt, userReference);
        return tweetJdbcCrudRepository.save(toCreate).toDomain(user);
    }

}
