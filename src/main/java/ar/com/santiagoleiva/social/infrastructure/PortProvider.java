package ar.com.santiagoleiva.social.infrastructure;

import ar.com.santiagoleiva.social.application.port.CreateTweetPort;
import ar.com.santiagoleiva.social.application.port.FindUserPort;
import ar.com.santiagoleiva.social.infrastructure.mock.MockCreateTweetPort;
import ar.com.santiagoleiva.social.infrastructure.mock.MockFindUserPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortProvider {

    @Bean
    public CreateTweetPort createTweetPort() {
        return new MockCreateTweetPort();
    }

    @Bean
    public FindUserPort findUserPort() {
        return new MockFindUserPort();
    }

}
