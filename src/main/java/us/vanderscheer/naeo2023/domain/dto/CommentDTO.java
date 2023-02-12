package us.vanderscheer.naeo2023.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import us.vanderscheer.naeo2023.domain.EntityType;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(name = "comment")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record CommentDTO(
        @JsonProperty(value = "comment_id", index = 1) UUID commentId,
        @JsonProperty(index = 2)Long version,
        @JsonProperty(value = "entity_type", index =3) EntityType entityType,
        @JsonProperty(value = "entity_id", index = 4) UUID entityId,
        @JsonProperty(value = "author_email", index = 5) String authorEmail,
        @JsonProperty(value = "author_name", index = 6) String authorName,
        @JsonProperty(index = 7) String content,
        @JsonProperty(index = 8) LocalDateTime created,
        @JsonProperty(index = 9) LocalDateTime updated) {
}
