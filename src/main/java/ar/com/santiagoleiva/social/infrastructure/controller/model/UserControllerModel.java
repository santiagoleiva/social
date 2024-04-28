package ar.com.santiagoleiva.social.infrastructure.controller.model;

import ar.com.santiagoleiva.social.domain.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserControllerModel(Long id, String username) {

    public static UserControllerModel fromDomain(User user) {
        return new UserControllerModel(user.id(), user.username());
    }

}
