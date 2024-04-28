package ar.com.santiagoleiva.social.infrastructure.controller.model;

import ar.com.santiagoleiva.social.domain.Tweet;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record TweetControllerModel(Long id, String content, LocalDateTime createdAt, UserControllerModel user) {

    public static TweetControllerModel fromDomain(Tweet tweet) {
        return new TweetControllerModel(
                tweet.id(),
                tweet.content(),
                tweet.createdAt(),
                UserControllerModel.fromDomain(tweet.user())
        );
    }

}
