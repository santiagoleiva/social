package ar.com.santiagoleiva.social.infrastructure.jdbc;

import ar.com.santiagoleiva.social.application.port.CreateTweetPort;
import ar.com.santiagoleiva.social.application.port.FindTweetPort;
import ar.com.santiagoleiva.social.domain.Tweet;
import ar.com.santiagoleiva.social.domain.User;
import ar.com.santiagoleiva.social.infrastructure.jdbc.model.TweetJdbcModel;
import ar.com.santiagoleiva.social.infrastructure.jdbc.model.UserJdbcModel;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class TweetJdbcRepository implements CreateTweetPort, FindTweetPort {

    private final TweetJdbcCrudRepository tweetJdbcCrudRepository;
    private final UserJdbcCrudRepository userJdbcCrudRepository;

    public TweetJdbcRepository(
            TweetJdbcCrudRepository tweetJdbcCrudRepository,
            UserJdbcCrudRepository userJdbcCrudRepository
    ) {
        this.tweetJdbcCrudRepository = tweetJdbcCrudRepository;
        this.userJdbcCrudRepository = userJdbcCrudRepository;
    }

    @Override
    public Tweet create(String content, User user, LocalDateTime createdAt) {
        final AggregateReference<UserJdbcModel, Long> userReference = AggregateReference.to(user.id());
        final TweetJdbcModel toCreate = new TweetJdbcModel(null, content, createdAt, userReference);
        return tweetJdbcCrudRepository.save(toCreate).toDomain(user);
    }

    @Override
    public List<Tweet> findByUserId(Collection<Long> userIds) {
        final Map<Long, User> usersById = userJdbcCrudRepository.streamByIdIn(userIds)
                .collect(toMap(UserJdbcModel::id, UserJdbcModel::toDomain));

        return tweetJdbcCrudRepository.streamByUserReferenceInOrderByCreatedAtDesc(userIds)
                .map(tweetJdbcModel -> tweetJdbcModel.toDomain(usersById.get(tweetJdbcModel.userReference().getId())))
                .toList();
    }

}
