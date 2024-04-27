package ar.com.santiagoleiva.social.application.port;

import ar.com.santiagoleiva.social.domain.User;
import ar.com.santiagoleiva.social.domain.exception.NotFoundException;

import java.util.Optional;

public interface FindUserPort {

    String USER_NOT_FOUND_MESSAGE_FORMAT = "User '%d' not found";

    Optional<User> findById(Long id);

    default User findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_MESSAGE_FORMAT.formatted(id)));
    }

}
