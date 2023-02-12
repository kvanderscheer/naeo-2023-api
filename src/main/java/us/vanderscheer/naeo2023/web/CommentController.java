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
import us.vanderscheer.naeo2023.domain.json.CommentAddRequest;
import us.vanderscheer.naeo2023.domain.json.CommentUpdateRequest;
import us.vanderscheer.naeo2023.exception.ApiError;
import us.vanderscheer.naeo2023.services.CommentService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/comments")
@Tag(name = "Comments", description = "Endpoints for comment operations.<br /><br />" +
        "Comments may be added to any supported object.<br /><br />" +
        "<b>Authorization is required to add, update, or delete comments.</b>")
public class CommentController {

    private final CommentService commentService;

    @Operation(
            summary = "Lists all comments.",
            description = "Lists all available comments.",
            tags = {"Comments"},
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
    @GetMapping
    public List<CommentDTO> getComments() {
        return commentService.getComments();
    }

    @Operation(
            summary = "Creates a comment",
            description = "Creates a new comment from the post body.<br /><br />" +
                    "Authorization is required for this method.",
            tags = {"Comments"},
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
    @PostMapping
    public ResponseEntity<CommentDTO> postComment(@RequestBody CommentAddRequest commentAddRequest,
                                                  UriComponentsBuilder builder) {
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
            description = "Updates a comment by the comment ID.<br /><br />" +
                    "Authorization is required for this method.",
            tags = {"Comments"},
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
    @PutMapping("/{comment_id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable("comment_id") UUID commentId,
                                                    @Parameter(description = "The ID of the comment to update.")
                                                    @RequestBody CommentUpdateRequest commentUpdateRequest,
                                                    UriComponentsBuilder builder) {
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
            tags = {"Comments"},
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
    @DeleteMapping("/{comment_id}")
    public void deleteComment(@PathVariable("comment_id")
                              @Parameter(description = "The ID of the comment to delete.<br /><br />" +
                                      "Authorization is required for this method.")
                              UUID commentId) {
        commentService.delete(commentId);
    }
}
