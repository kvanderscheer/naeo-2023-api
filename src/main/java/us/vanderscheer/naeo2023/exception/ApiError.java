package us.vanderscheer.naeo2023.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name = "apiError")
public record ApiError(
        String path,
        String message,
        @JsonProperty("status_code") int statusCode,
        @JsonProperty("timestamp") LocalDateTime localDateTime) {
}
