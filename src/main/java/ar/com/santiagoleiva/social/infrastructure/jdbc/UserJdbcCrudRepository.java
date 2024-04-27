package ar.com.santiagoleiva.social.infrastructure.jdbc;

import ar.com.santiagoleiva.social.infrastructure.jdbc.model.UserJdbcModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserJdbcCrudRepository extends CrudRepository<UserJdbcModel, Long> {

    List<UserJdbcModel> findByFollowersFollowerUserId(Long followerUserId);

}
