package ar.com.santiagoleiva.social.application.port;

import ar.com.santiagoleiva.social.domain.User;

import java.util.List;

public interface UserFollowPort {

    void saveFollow(User follower, User followed);

    List<User> getFollowedUsersByUserId(Long followerUserId);

}
