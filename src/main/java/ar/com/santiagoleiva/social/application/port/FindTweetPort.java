package ar.com.santiagoleiva.social.application.port;

import ar.com.santiagoleiva.social.domain.Tweet;

import java.util.List;

public interface FindTweetPort {

    List<Tweet> byUserId(Long... userId);

}
