package ar.com.santiagoleiva.social.infrastructure.jdbc;

import ar.com.santiagoleiva.social.infrastructure.jdbc.model.TweetJdbcModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.stream.Stream;

public interface TweetJdbcCrudRepository extends CrudRepository<TweetJdbcModel, Long> {

    Stream<TweetJdbcModel> streamByUserReferenceInOrderByCreatedAtDesc(Collection<Long> userId);

}
