package ar.com.santiagoleiva.social.application.usecase;

import ar.com.santiagoleiva.social.application.port.FindUserPort;
import ar.com.santiagoleiva.social.application.port.UserFollowPort;
import ar.com.santiagoleiva.social.domain.User;
import ar.com.santiagoleiva.social.domain.exception.NonProcessableException;

import java.util.Objects;

public class FollowUserUseCase {

    private static final String SAME_FOLLOWED_FOLLOWER_MESSAGE = "Follower and followed should not be the same";

    private final FindUserPort findUserPort;
    private final UserFollowPort userFollowPort;

    public FollowUserUseCase(FindUserPort findUserPort, UserFollowPort userFollowPort) {
        this.findUserPort = findUserPort;
        this.userFollowPort = userFollowPort;
    }

    public void execute(Long followerId, Long followedId) {
        doValidations(followerId, followedId);
        saveRelation(getUserById(followerId), getUserById(followedId));
    }

    private void doValidations(Long followerId, Long followedId) {
        if (Objects.equals(followerId, followedId)) {
            throw new NonProcessableException(SAME_FOLLOWED_FOLLOWER_MESSAGE);
        }
    }

    private User getUserById(Long followerId) {
        return findUserPort.findByIdOrThrow(followerId);
    }

    private void saveRelation(User follower, User followed) {
        userFollowPort.saveFollow(follower, followed);
    }

}
