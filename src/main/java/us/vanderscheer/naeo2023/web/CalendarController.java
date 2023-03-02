package us.vanderscheer.naeo2023.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.vanderscheer.naeo2023.domain.dto.CalendarDTO;
import us.vanderscheer.naeo2023.exception.ApiError;
import us.vanderscheer.naeo2023.services.CalendarService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/calendars")
@Tag(name = "Calendars", description = "Endpoints for calendar operations")
public class CalendarController {

    private final CalendarService calendarService;

    @Operation(
            summary = "Lists all calendars",
            description = "Lists all available calendars",
            tags = { "Calendars" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = CalendarDTO.class))
                                    )}
                    ),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @GetMapping
    List<CalendarDTO> getCalendars() {
        return calendarService.getCalendars();
    }

    @Operation(
            summary = "Get a Calendar",
            description = "get an calendar by the calendar_id.",
            tags = {"Calendars"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CalendarDTO.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @GetMapping("/{calendar_id}")
    CalendarDTO getCalendar(@PathVariable("calendar_id")
                            @Parameter(description = "The ID of the calendar to find.")
                            UUID calendarId) {
        return calendarService.getCalendar(calendarId);
    }
}
