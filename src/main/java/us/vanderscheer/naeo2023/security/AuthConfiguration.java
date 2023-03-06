package us.vanderscheer.naeo2023.security;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
@NoArgsConstructor
public class AuthConfiguration extends WebSecurityConfiguration {
    private static final String API_KEY_AUTH_HEADER_NAME = "x-api-key";

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        ApiKeyAuthFilter filter = new ApiKeyAuthFilter(API_KEY_AUTH_HEADER_NAME);
        filter.setAuthenticationManager(new DemoApiKeyAuthManager());

        http.authorizeRequests((requests) -> requests
                        .requestMatchers("/hello-world.html").permitAll()
                        //.requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/v1/**").authenticated()
                )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(filter)
                .csrf().disable();
        return http.build();
    }

}
