package ar.com.santiagoleiva.social.application.port;

import ar.com.santiagoleiva.social.domain.User;

public interface FollowUserPort {

    void saveFollow(User follower, User followed);

}
