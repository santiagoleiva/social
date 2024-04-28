package ar.com.santiagoleiva.social.infrastructure.jdbc;

import ar.com.santiagoleiva.social.infrastructure.jdbc.model.UserJdbcModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.stream.Stream;

public interface UserJdbcCrudRepository extends CrudRepository<UserJdbcModel, Long> {

    Stream<UserJdbcModel> streamByIdIn(Collection<Long> ids);

}
