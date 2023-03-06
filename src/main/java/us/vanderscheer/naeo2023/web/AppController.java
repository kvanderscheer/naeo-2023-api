package us.vanderscheer.naeo2023.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.vanderscheer.naeo2023.domain.dto.VersionInfoDTO;

import java.time.ZoneId;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
@Tag(name = "Application", description = "Endpoints for application information")
public class AppController {

    private final BuildProperties buildProperties;

    /*
     * Get version info
     */
    @Operation(
            summary = "Get version info",
            description = "Gets API version information.",
            tags = { "Application" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = VersionInfoDTO.class))
                    ),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @SecurityRequirement(name = "x-api-key")
    @RequestMapping(value = "/version", method = GET, produces = {"application/json"})
    public ResponseEntity<VersionInfoDTO> getVersion() {
        return new ResponseEntity<>(new VersionInfoDTO(
                buildProperties.getName(),
                buildProperties.getVersion(),
                buildProperties.getTime().atZone(ZoneId.of("UTC"))), HttpStatus.OK);
    }
}
