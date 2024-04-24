package ar.com.santiagoleiva.social.application.port;

import java.util.Optional;
import ar.com.santiagoleiva.social.domain.User;

public interface FindUserPort {

    Optional<User> byId(Long id);

}
