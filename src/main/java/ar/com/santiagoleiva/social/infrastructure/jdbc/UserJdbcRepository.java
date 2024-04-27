package ar.com.santiagoleiva.social.infrastructure.jdbc;

import ar.com.santiagoleiva.social.application.port.FindUserPort;
import ar.com.santiagoleiva.social.application.port.FollowUserPort;
import ar.com.santiagoleiva.social.domain.User;
import ar.com.santiagoleiva.social.infrastructure.jdbc.model.FollowerReferenceModel;
import ar.com.santiagoleiva.social.infrastructure.jdbc.model.UserJdbcModel;

import java.util.Optional;

public class UserJdbcRepository implements FindUserPort, FollowUserPort {

    private final UserJdbcCrudRepository userJdbcCrudRepository;

    public UserJdbcRepository(UserJdbcCrudRepository userJdbcCrudRepository) {
        this.userJdbcCrudRepository = userJdbcCrudRepository;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userJdbcCrudRepository.findById(id)
                .map(UserJdbcModel::toDomain);
    }

    @Override
    public void saveFollow(User follower, User followed) {
        final UserJdbcModel followedJdbcModel = userJdbcCrudRepository.findById(followed.id()).orElseThrow();
        followedJdbcModel.followers().add(new FollowerReferenceModel(follower.id()));
        userJdbcCrudRepository.save(followedJdbcModel);
    }

}
