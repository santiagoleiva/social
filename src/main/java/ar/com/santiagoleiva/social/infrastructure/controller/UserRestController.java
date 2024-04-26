package ar.com.santiagoleiva.social.infrastructure.controller;

import ar.com.santiagoleiva.social.application.usecase.CreateTweetUseCase;
import ar.com.santiagoleiva.social.domain.Tweet;
import ar.com.santiagoleiva.social.infrastructure.controller.model.CreateTweetRequest;
import ar.com.santiagoleiva.social.infrastructure.controller.model.TweetControllerModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final CreateTweetUseCase createTweetUseCase;

    public UserRestController(CreateTweetUseCase createTweetUseCase) {
        this.createTweetUseCase = createTweetUseCase;
    }

    @PostMapping("/{userId}/tweets")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TweetControllerModel> createTweet(@PathVariable Long userId, @RequestBody CreateTweetRequest request) {
        final Tweet createdTweet = createTweetUseCase.execute(userId, request.content());
        final TweetControllerModel responseBody = TweetControllerModel.fromDomain(createdTweet);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

}
