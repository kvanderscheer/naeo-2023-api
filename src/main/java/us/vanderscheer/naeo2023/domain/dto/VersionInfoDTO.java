package us.vanderscheer.naeo2023.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.ZonedDateTime;

@Schema(name = "versionInfo")
public class VersionInfoDTO {
    @JsonProperty("name")
    private String appName;
    @JsonProperty("version")
    private String appVersion;
    @JsonProperty("build_date")
    private ZonedDateTime appBuildDateTime;

    public VersionInfoDTO(String appName, String appVersion, ZonedDateTime appBuildDateTime) {
        this.appName = appName;
        this.appVersion = appVersion;
        this.appBuildDateTime = appBuildDateTime;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public ZonedDateTime getAppBuildDateTime() {
        return appBuildDateTime;
    }

    public void setAppBuildDateTime(ZonedDateTime appBuildDateTime) {
        this.appBuildDateTime = appBuildDateTime;
    }
}
