package ar.com.santiagoleiva.social.infrastructure.jdbc;

import ar.com.santiagoleiva.social.application.port.FindUserPort;
import ar.com.santiagoleiva.social.application.port.FollowUserPort;
import ar.com.santiagoleiva.social.domain.User;
import ar.com.santiagoleiva.social.infrastructure.jdbc.model.UserJdbcModel;

import java.util.Optional;

public class UserJdbcRepository implements FindUserPort, FollowUserPort {

    private final UserJdbcCrudRepository userJdbcCrudRepository;

    public UserJdbcRepository(UserJdbcCrudRepository userJdbcCrudRepository) {
        this.userJdbcCrudRepository = userJdbcCrudRepository;
    }

    @Override
    public Optional<User> byId(Long id) {
        return userJdbcCrudRepository.findById(id)
                .map(UserJdbcModel::toDomain);
    }

    @Override
    public void saveFollow(User follower, User followed) {
        throw new IllegalStateException("Not implemented yet");
    }

}
