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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import us.vanderscheer.naeo2023.domain.dto.CommentDTO;
import us.vanderscheer.naeo2023.domain.dto.EventDTO;
import us.vanderscheer.naeo2023.domain.json.CommentAddRequest;
import us.vanderscheer.naeo2023.domain.json.CommentUpdateRequest;
import us.vanderscheer.naeo2023.exception.ApiError;
import us.vanderscheer.naeo2023.services.CommentService;
import us.vanderscheer.naeo2023.services.EventService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/events")
@Tag(name = "Events", description = "Endpoints for calendar events")
public class EventController {

    private final EventService eventService;

    private final CommentService commentService;

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
    @SecurityRequirement(name = "x-api-key")
    @GetMapping
    List<EventDTO> getEvents(@RequestParam("calendar_id")
                             @Parameter(description = "The ID of the calendar.") UUID calendarId,
                             @RequestParam(value = "from_date", required = false)
                             @Parameter(description = "The from (start) date of the events to retrieve.") LocalDate fromDate,
                             @RequestParam(value = "to_date", required = false)
                             @Parameter(description = "The to (end) date of the events to retrieve.") LocalDate toDate) {
        return eventService.getEvents(calendarId, fromDate, toDate);
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
    @SecurityRequirement(name = "x-api-key")
    @GetMapping("/{event_id}")
    EventDTO getEvent(@PathVariable("event_id")
                      @Parameter(description = "The ID of the event to find.")
                      UUID eventId) {
        return eventService.getEvent(eventId);
    }

    @Operation(
            summary = "Lists all comments.",
            description = "Lists all available comments for an event.",
            tags = {"Events"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = CommentDTO.class))
                                    )}
                    ),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @SecurityRequirement(name = "x-api-key")
    @GetMapping("/{event_id}/comments")
    List<CommentDTO> getComments(@PathVariable("event_id")
                                 @Parameter(description = "The ID of the event to find.") UUID eventId) {
        return commentService.getCommentsByEntity(eventId);
    }

    @Operation(
            summary = "Get a comment.",
            description = "Get a comment by its comment ID.",
            tags = {"Events"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = CommentDTO.class))
                                    )}
                    ),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @SecurityRequirement(name = "x-api-key")
    @GetMapping("/{event_id}/comments/{comment_id}")
    CommentDTO getComment(
            @PathVariable("event_id")
            @Parameter(description = "The ID of the event to find.") UUID eventId,
            @PathVariable("comment_id")
            @Parameter(description = "The ID of the comment to update.") UUID commentId) {
        // TODO: validate eventId
        return commentService.getCommentById(commentId);
    }

    @Operation(
            summary = "Creates a comment",
            description = "Creates a new comment from the post body.<br /><br />",
            tags = {"Events"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentDTO.class))
                    ),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @SecurityRequirement(name = "x-api-key")
    @PostMapping("/{event_id}/comments")
    public ResponseEntity<CommentDTO> postComment(
            @PathVariable("event_id")
            @Parameter(description = "The ID of the event to create the comment for.") UUID eventId,
            @RequestBody CommentAddRequest commentAddRequest,
            UriComponentsBuilder builder) {
        // TODO: validate commentAddRequest
        CommentDTO created = commentService.add(commentAddRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder
                .path("/comments/{comment_id}")
                .buildAndExpand(created.commentId())
                .toUri());
        return new ResponseEntity<>(created, headers, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Updates a comment.",
            description = "Updates a comment by the event ID and comment ID.<br /><br />",
            tags = {"Events"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request: No changes found", responseCode = "400",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
                    @ApiResponse(description = "Not found", responseCode = "404",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @SecurityRequirement(name = "x-api-key")
    @PutMapping("/events/{event_id}/comments/{comment_id}")
    public ResponseEntity<CommentDTO> updateComment(
            @PathVariable("event_id")
            @Parameter(description = "The ID of the event of the comment tp update.") UUID eventId,
            @PathVariable("comment_id") UUID commentId,
            @Parameter(description = "The ID of the comment to update.")
            @RequestBody CommentUpdateRequest commentUpdateRequest,
            UriComponentsBuilder builder) {
        // TODO: validate commentUpdateRequest
        CommentDTO updated = commentService.update(commentId, commentUpdateRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder
                .path("/comments/{comment_id}")
                .buildAndExpand(updated.commentId())
                .toUri());
        return new ResponseEntity<>(updated, headers, HttpStatus.OK);
    }

    @Operation(
            summary = "Deletes a comment.",
            description = "Deletes a comment by the comment ID.",
            tags = {"Events"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentDTO.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @SecurityRequirement(name = "x-api-key")
    @DeleteMapping("/events/{event_id}/comments/{comment_id}")
    public void deleteComment(
            @PathVariable("event_id")
            @Parameter(description = "The ID of the event of the comment to delete.") UUID eventId,
            @PathVariable("comment_id")
            @Parameter(description = "The ID of the comment to delete.<br /><br />" +
                    "Authorization is required for this method.")
            UUID commentId) {
        // TODO: validate eventId
        commentService.delete(commentId);
    }
}
