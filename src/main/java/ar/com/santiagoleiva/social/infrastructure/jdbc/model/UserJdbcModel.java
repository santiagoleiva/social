package ar.com.santiagoleiva.social.infrastructure.jdbc.model;

import ar.com.santiagoleiva.social.domain.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Table("user")
public record UserJdbcModel(
        @Id Long id,
        String username,
        @MappedCollection(idColumn = "followed_user_id")
        Set<FollowerReferenceModel> followers,
        @MappedCollection(idColumn = "follower_user_id")
        Set<FollowingReferenceModel> following
) {

    public User toDomain() {
        return new User(id, username);
    }

}
