package ar.com.santiagoleiva.social.application.usecase;

import ar.com.santiagoleiva.social.application.port.FindTweetPort;
import ar.com.santiagoleiva.social.application.port.UserFollowPort;
import ar.com.santiagoleiva.social.domain.Tweet;

import java.util.List;

public class GetUserTimelineUseCase {

    private final UserFollowPort userFollowPort;
    private final FindTweetPort findTweetPort;

    public GetUserTimelineUseCase(UserFollowPort userFollowPort, FindTweetPort findTweetPort) {
        this.userFollowPort = userFollowPort;
        this.findTweetPort = findTweetPort;
    }

    public List<Tweet> execute(Long userId) {
        final List<Long> followedUserIds = userFollowPort.getFollowedUserIds(userId);
        return findTweetPort.findByUserId(followedUserIds);
    }

}
