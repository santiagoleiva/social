package ar.com.santiagoleiva.social.application.usecase;

import ar.com.santiagoleiva.social.application.port.FindUserPort;
import ar.com.santiagoleiva.social.application.port.FollowUserPort;
import ar.com.santiagoleiva.social.domain.User;
import ar.com.santiagoleiva.social.domain.exception.NonProcessableException;

import java.util.Objects;

public class FollowUserUseCase {

    private static final String SAME_FOLLOWED_FOLLOWER_MESSAGE = "Follower and followed should not be the same";

    private final FindUserPort findUserPort;
    private final FollowUserPort followUserPort;

    public FollowUserUseCase(FindUserPort findUserPort, FollowUserPort followUserPort) {
        this.findUserPort = findUserPort;
        this.followUserPort = followUserPort;
    }

    void execute(Long followerId, Long followedId) {
        doValidations(followerId, followedId);
        saveRelation(getUserById(followerId), getUserById(followedId));
    }

    private void doValidations(Long followerId, Long followedId) {
        if (Objects.equals(followerId, followedId)) {
            throw new NonProcessableException(SAME_FOLLOWED_FOLLOWER_MESSAGE);
        }
    }

    private User getUserById(Long followerId) {
        return findUserPort.byIdOrThrow(followerId);
    }

    private void saveRelation(User follower, User followed) {
        followUserPort.saveFollow(follower, followed);
    }

}
