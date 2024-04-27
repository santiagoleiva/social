package ar.com.santiagoleiva.social.application.usecase;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Create tweet use case tests")
class CreateTweetUseCaseTest {

    @Autowired
    CreateTweetUseCase createTweetUseCase;

}