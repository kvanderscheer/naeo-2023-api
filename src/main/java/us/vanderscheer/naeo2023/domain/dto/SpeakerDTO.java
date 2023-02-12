package us.vanderscheer.naeo2023.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(name = "speaker")
public record SpeakerDTO(
        @NotNull @JsonProperty("speaker_id") UUID speakerId,
        @NotBlank @JsonProperty("first_name") String firstName,
        @NotBlank @JsonProperty("last_name") String lastName,
        @NotBlank String email,
        String company,
        String bio) {
}
