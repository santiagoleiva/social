package ar.com.santiagoleiva.social.infrastructure.jdbc.model;

import ar.com.santiagoleiva.social.domain.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("user")
public record UserJdbcModel(@Id Long id, String username) {

    public User toDomain() {
        return new User(id, username);
    }

}
