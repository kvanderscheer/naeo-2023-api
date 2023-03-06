package us.vanderscheer.naeo2023.domain.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import us.vanderscheer.naeo2023.domain.EntityType;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "comments")
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "comment_id")
    private UUID commentId;

    @Version
    private Long version;

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_type")
    private EntityType entityType;

    @Column(name = "entity_id")
    private UUID entityId;

    @Column(name = "author_email")
    private String authorEmail;

    @Column(name = "author_name")
    private String authorName;

    private String content;

    private LocalDateTime created;

    private LocalDateTime updated;

    public Comment(EntityType entityType, UUID entityId, String authorEmail, String authorName, String content) {
        this.entityType = entityType;
        this.entityId = entityId;
        this.authorEmail = authorEmail;
        this.authorName = authorName;
        this.content = content;
    }

    @PrePersist
    private void prePersist() {
        created = LocalDateTime.now();
        updated = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        updated = LocalDateTime.now();
    }
}
