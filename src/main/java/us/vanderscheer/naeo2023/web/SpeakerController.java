package us.vanderscheer.naeo2023.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.vanderscheer.naeo2023.domain.dto.SpeakerDTO;
import us.vanderscheer.naeo2023.exception.ApiError;
import us.vanderscheer.naeo2023.services.SpeakerService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/speakers")
@Tag(name = "Speakers", description = "Endpoints for speakers")
public class SpeakerController {

    private final SpeakerService speakerService;

    @Operation(
            summary = "Get speakers",
            description = "Get a list of all speakers.",
            tags = {"Speakers"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = SpeakerDTO.class))
                                    )}
                    ),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @SecurityRequirement(name = "x-api-key")
    @GetMapping
    public List<SpeakerDTO> getSpeakers() {
        return speakerService.getSpeakers();
    }

    @Operation(
            summary = "Get a speaker",
            description = "Get a speaker by the speaker_id.",
            tags = {"Speakers"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SpeakerDTO.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @SecurityRequirement(name = "x-api-key")
    @GetMapping("/{speaker_id}")
    public SpeakerDTO getSpeaker(@PathVariable("speaker_id")
                                 @Parameter(description = "The ID of the speaker to find.")
                                 UUID speakerId) {
        return speakerService.getSpeaker(speakerId);
    }
}
