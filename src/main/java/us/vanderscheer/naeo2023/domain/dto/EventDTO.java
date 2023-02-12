package us.vanderscheer.naeo2023.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Schema(name = "event")
public record EventDTO(
        @NotNull @JsonProperty("event_id") UUID eventId,
        @NotNull @JsonProperty("calendar_id") UUID calendarId,
        @JsonProperty("from_datetime") LocalDateTime fromDateTime,
        @JsonProperty("to_datetime") LocalDateTime toDateTime,
        @NotBlank(message = "The event name is required") String name,
        String description,
        String location,
        List<SpeakerDTO> speakers) {
}
