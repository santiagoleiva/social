package ar.com.santiagoleiva.social.infrastructure.jdbc.model;

import ar.com.santiagoleiva.social.domain.Tweet;
import ar.com.santiagoleiva.social.domain.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("tweet")
public record TweetJdbcModel(
        @Id Long id,
        String content,
        LocalDateTime createdAt,
        @Column("user_id")
        AggregateReference<UserJdbcModel, Long> userReference
) {

    public Tweet toDomain(User user) {
        return new Tweet(id, content, user, createdAt);
    }

}
