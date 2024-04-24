package ar.com.santiagoleiva.social.domain;

import java.time.LocalDate;

public record Tweet(Long id, String content, User user, LocalDate createdAt) {}
