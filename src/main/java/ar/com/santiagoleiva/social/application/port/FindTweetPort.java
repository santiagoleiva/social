package ar.com.santiagoleiva.social.application.port;

import ar.com.santiagoleiva.social.domain.Tweet;

import java.util.Collection;
import java.util.List;

public interface FindTweetPort {

    List<Tweet> findByUserId(Collection<Long> userId);

}
