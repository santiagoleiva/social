package ar.com.santiagoleiva.social.infrastructure.jdbc.model;

import org.springframework.data.relational.core.mapping.Table;

@Table("follow")
public record FollowerReferenceModel(Long followerUserId) {
}
