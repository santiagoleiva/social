package ar.com.santiagoleiva.social.infrastructure.controller;

import ar.com.santiagoleiva.social.application.usecase.CreateTweetUseCase;
import ar.com.santiagoleiva.social.application.usecase.FollowUserUseCase;
import ar.com.santiagoleiva.social.application.usecase.GetUserTimelineUseCase;
import ar.com.santiagoleiva.social.domain.Tweet;
import ar.com.santiagoleiva.social.infrastructure.controller.model.CreateTweetRequest;
import ar.com.santiagoleiva.social.infrastructure.controller.model.FollowUserRequest;
import ar.com.santiagoleiva.social.infrastructure.controller.model.TweetControllerModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final CreateTweetUseCase createTweetUseCase;
    private final FollowUserUseCase followUserUseCase;
    private final GetUserTimelineUseCase getUserTimelineUseCase;

    public UserRestController(
            CreateTweetUseCase createTweetUseCase,
            FollowUserUseCase followUserUseCase,
            GetUserTimelineUseCase getUserTimelineUseCase
    ) {
        this.createTweetUseCase = createTweetUseCase;
        this.followUserUseCase = followUserUseCase;
        this.getUserTimelineUseCase = getUserTimelineUseCase;
    }

    @PostMapping("/{userId}/tweets")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TweetControllerModel> createTweet(@PathVariable Long userId, @RequestBody CreateTweetRequest request) {
        final Tweet createdTweet = createTweetUseCase.execute(userId, request.content());
        final TweetControllerModel responseBody = TweetControllerModel.fromDomain(createdTweet);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @PostMapping("/{userId}/follows")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> saveFollow(@PathVariable Long userId, @RequestBody FollowUserRequest request) {
        followUserUseCase.execute(userId, request.followedUserId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/timeline")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TweetControllerModel>> getUserTimeline(@PathVariable Long userId) {
        final List<TweetControllerModel> followingUserTweets = getUserTimelineUseCase.execute(userId)
                .stream()
                .map(TweetControllerModel::fromDomain)
                .toList();

        return ResponseEntity.ok(followingUserTweets);
    }

}
