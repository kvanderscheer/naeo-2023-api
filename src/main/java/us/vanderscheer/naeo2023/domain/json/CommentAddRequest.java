package us.vanderscheer.naeo2023.domain.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import us.vanderscheer.naeo2023.domain.EntityType;

import java.util.UUID;

@Schema(name = "commentAddRequest")
public record CommentAddRequest(
        @JsonProperty("entity_type") EntityType entityType,
        @JsonProperty("entity_id") UUID entityId,
        @JsonProperty("author_email") String authorEmail,
        @JsonProperty("author_name") String authorName,
        String content
) {
}
