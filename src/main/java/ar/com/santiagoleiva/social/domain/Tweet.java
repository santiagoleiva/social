package ar.com.santiagoleiva.social.domain;

import java.time.LocalDateTime;

public record Tweet(Long id, String content, User user, LocalDateTime createdAt) {
}
