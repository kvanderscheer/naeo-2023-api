package us.vanderscheer.naeo2023.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Slf4j
public class DemoApiKeyAuthManager implements AuthenticationManager {

    /*
     * This is the API key that is used to authenticate the user to the API. It is hard-coded
     * for demo purposes. In a real application, this would be stored in a database and retrieved
     * from there.
     */
    private static final String API_KEY_AUTH_KEY = "0df6dcef-e613-44af-a5fe-de41d64a91b2";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = (String) authentication.getPrincipal();

        if (API_KEY_AUTH_KEY.equals(principal)) {
            authentication.setAuthenticated(true);
            return authentication;
        } else {
            log.info("Bad credentials: [principal: {%s}]".formatted(principal));
            throw new BadCredentialsException("The API key was not found");
        }
    }
}
