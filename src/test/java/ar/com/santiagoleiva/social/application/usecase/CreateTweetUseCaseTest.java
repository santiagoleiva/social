package ar.com.santiagoleiva.social.application.usecase;

import ar.com.santiagoleiva.social.domain.Tweet;
import ar.com.santiagoleiva.social.domain.exception.InvalidException;
import ar.com.santiagoleiva.social.domain.exception.NonProcessableException;
import ar.com.santiagoleiva.social.domain.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Create tweet use case tests")
class CreateTweetUseCaseTest {

    @Autowired
    private CreateTweetUseCase createTweetUseCase;

    @MockBean
    private Clock clock;

    @BeforeEach
    void beforeEach() {
        when(clock.instant()).thenReturn(Instant.parse("2024-04-01T10:00:00.00Z"));
        when(clock.getZone()).thenReturn(ZoneId.of("UTC"));
    }

    @Test
    @DisplayName("When content is null it should throw an `InvalidException`")
    void whenContentIsNullItShouldThrowAnInvalidException() {
        assertThatThrownBy(() -> createTweetUseCase.execute(1L, null))
                .isInstanceOf(InvalidException.class)
                .hasMessage("Content must not be null");
    }

    @Test
    @DisplayName("When content length is greater than max size it should throw a `NonProcessableException`")
    void whenContentLengthIsGreaterThanMaxLengthItShouldThrowNonProcessableException() {
        final String largeContent = """
                Large content. Large content. Large content. Large content. Large content. Large content. Large content. Large content. 
                Large content. Large content. Large content. Large content. Large content. Large content. Large content. Large content. 
                Large content. Large content. Large content. Large content. Large content. Large content. Large content. Large content. 
                Large content. Large content. Large content. Large content. Large content. Large content. Large content. Large content. 
                """;

        assertThatThrownBy(() -> createTweetUseCase.execute(1L, largeContent))
                .isInstanceOf(NonProcessableException.class)
                .hasMessage("The character limit has been exceeded (280)");
    }

    @Test
    @DisplayName("When user does not exists it should throw a `NotFoundException`")
    void whenUserDoesNotExistsItShouldThrowNotFoundException() {
        assertThatThrownBy(() -> createTweetUseCase.execute(-10L, "Valid content"))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("User '-10' not found");
    }

    @Test
    @DisplayName("It should publish a message without errors")
    void itShouldPublishMessageWithoutErrors() {
        final Tweet result = createTweetUseCase.execute(1L, "Hello world!");

        assertAll(
                () -> assertNotNull(result.id()),
                () -> assertEquals("Hello world!", result.content()),
                () -> assertEquals(LocalDateTime.of(2024, Month.APRIL, 1, 10, 0, 0), result.createdAt())
        );
    }

}