package us.vanderscheer.naeo2023.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.vanderscheer.naeo2023.domain.dto.CalendarDTO;
import us.vanderscheer.naeo2023.services.CalendarService;

import java.util.List;

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
}
