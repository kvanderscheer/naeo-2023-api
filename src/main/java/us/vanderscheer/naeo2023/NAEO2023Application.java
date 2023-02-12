package us.vanderscheer.naeo2023;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
@OpenAPIDefinition(
		servers = {
				@Server(url = "https://naeo2023api.vanderscheer.us"),
				@Server(url = "http://localhost:8080")
		},
		info = @Info(title = "NAEO 2023 Conference Demo API",
				version = "1.0", license =
		@License(name = "MIT", url = "https://en.wikipedia.org/wiki/MIT_License"))
)
@SecurityScheme(name = "x-api-key", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER)
public class NAEO2023Application {

	public static void main(String[] args) {

		SpringApplication.run(NAEO2023Application.class, args);
	}

	@PostConstruct
	private void init() {
		// set the JVM's default timezone to UTC
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
}
