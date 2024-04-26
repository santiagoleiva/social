package ar.com.santiagoleiva.social.infrastructure.configuration;

import ar.com.santiagoleiva.social.application.port.CreateTweetPort;
import ar.com.santiagoleiva.social.application.port.FindUserPort;
import ar.com.santiagoleiva.social.infrastructure.jdbc.UserJdbcCrudRepository;
import ar.com.santiagoleiva.social.infrastructure.jdbc.UserJdbcRepository;
import ar.com.santiagoleiva.social.infrastructure.mock.MockCreateTweetPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortProvider {

    @Bean
    public CreateTweetPort createTweetPort() {
        return new MockCreateTweetPort();
    }

    @Bean
    public FindUserPort findUserPort(UserJdbcCrudRepository userJdbcCrudRepository) {
        return new UserJdbcRepository(userJdbcCrudRepository);
    }

}
