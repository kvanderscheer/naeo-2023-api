package us.vanderscheer.naeo2023.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(name = "calendar")
public record CalendarDTO(
        @JsonProperty("calendar_id") UUID calendarId,
        String name,
        String description,
        String location) {
}
