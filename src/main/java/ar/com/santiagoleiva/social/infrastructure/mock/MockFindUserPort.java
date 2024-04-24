package ar.com.santiagoleiva.social.infrastructure.mock;

import ar.com.santiagoleiva.social.application.port.FindUserPort;
import ar.com.santiagoleiva.social.domain.User;

import java.util.Optional;

public class MockFindUserPort implements FindUserPort {

    @Override
    public Optional<User> byId(Long id) {
        return Optional.of(new User(1L, "nomi.marks"));
    }

}
