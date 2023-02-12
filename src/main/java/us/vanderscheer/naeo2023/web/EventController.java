package us.vanderscheer.naeo2023.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import us.vanderscheer.naeo2023.domain.dto.EventDTO;
import us.vanderscheer.naeo2023.exception.ApiError;
import us.vanderscheer.naeo2023.services.EventService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/events")
@Tag(name = "Events", description = "Endpoints for calendar events")
public class EventController {

    private final EventService eventService;

    @Operation(
            summary = "Get events",
            description = "get a list of all events by calendar_id.",
            tags = {"Events"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = EventDTO.class))
                                    )}
                    ),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @GetMapping
    List<EventDTO> getEvents(@RequestParam("calendar_id")
                             @Parameter(description = "The ID of the calendar.")
                             UUID calendarId) {
        return eventService.getEvents(calendarId);
    }

    @Operation(
            summary = "Get an event",
            description = "get an event by the event_id.",
            tags = {"Events"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventDTO.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @GetMapping("/{event_id}")
    EventDTO getEvent(@PathVariable("event_id")
                      @Parameter(description = "The ID of the event to find.")
                      UUID eventId) {
        return eventService.getEvent(eventId);
    }
}
