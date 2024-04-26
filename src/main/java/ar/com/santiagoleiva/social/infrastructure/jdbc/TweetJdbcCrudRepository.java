package ar.com.santiagoleiva.social.infrastructure.jdbc;

import ar.com.santiagoleiva.social.infrastructure.jdbc.model.TweetJdbcModel;
import org.springframework.data.repository.CrudRepository;

public interface TweetJdbcCrudRepository extends CrudRepository<TweetJdbcModel, Long> {
}
