package ar.com.santiagoleiva.social.application.usecase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Get user timeline use case tests")
class GetUserTimelineUseCaseTest {

    @Autowired
    GetUserTimelineUseCase getUserTimelineUseCase;

    @Test
    @DisplayName("When user is not following to anyone it should return an empty list")
    void whenUserIsNotFollowingToAnyoneItShouldReturnAnEmptyList() {
        assertThat(getUserTimelineUseCase.execute(1L))
                .isEmpty();
    }

}
