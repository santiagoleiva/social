package ar.com.santiagoleiva.social.application.usecase;

import ar.com.santiagoleiva.social.domain.exception.NonProcessableException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Follow user use case tests")
class FollowUserUseCaseTest {

    @Autowired
    private FollowUserUseCase followUserUseCase;

    @Test
    @DisplayName("When `followerId` and `followedId` are the same it should throw a `NonProcessableException`")
    void whenFollowerIdAndFollowedIdAreTheSameItShouldThrowNonProcessableException() {
        assertThatThrownBy(() -> followUserUseCase.execute(1L, 1L))
                .isInstanceOf(NonProcessableException.class)
                .hasMessage("Follower and followed should not be the same");
    }

    @Test
    @DisplayName("User should follow to another user without errors")
    void userShouldFollowToAnotherUserWithoutErrors() {
        assertDoesNotThrow(() -> followUserUseCase.execute(1L, 2L));
    }

}
