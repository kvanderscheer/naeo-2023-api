package us.vanderscheer.naeo2023.domain.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "commentUpdateRequest")
public record CommentUpdateRequest(
        @JsonProperty("author_email") String authorEmail,
        @JsonProperty("author_name") String authorName,
        String content
) {
}
