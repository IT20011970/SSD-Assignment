package lk.agri.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.agri.entity.UserAccount;
import lk.agri.repository.UserAccountRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Component
public class CSRFFilter extends OncePerRequestFilter {

    //    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private JwtCache jwtCache;
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException, RuntimeException {

        String authorizationHeader = httpServletRequest.getHeader("X-CSRF-TOKEN");
        String authorizaUser = httpServletRequest.getHeader("USER");

        if (authorizaUser != null) {
            UserAccount userAccountObj = userAccountRepository.findByEmail(authorizaUser);
            if (userAccountObj == null) {
                // Handle the case where the user account is not found
                logger.warn("User account not found for email: " + authorizaUser);

                // You can choose how to handle this situation; for example, you might want to return a 404 Not Found response.
                // Here, we'll return a 404 response as an example.
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("path", "/");

                // Convert the JSON object to a string
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonResponse = objectMapper.writeValueAsString(errorResponse);

                // Set the HTTP response status to 403 Forbidden (or any other appropriate status code)
                httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);

                // Set the response content type to JSON
                httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

                // Write the JSON error response to the response body
                httpServletResponse.getWriter().write(jsonResponse);
                return;
            } else {
                if (!httpServletRequest.getRequestURI().startsWith("/farmer/csrf-token")) {
                    if (authorizationHeader == null || !authorizationHeader.equals(userAccountObj.getToken())) {
                        logger.warn("JWT Token does not match expected value or is missing.");

                        // Create a JSON object for the error message
                        Map<String, String> errorResponse = new HashMap<>();
                        errorResponse.put("path", "/");

                        // Convert the JSON object to a string
                        ObjectMapper objectMapper = new ObjectMapper();
                        String jsonResponse = objectMapper.writeValueAsString(errorResponse);

                        // Set the HTTP response status to 403 Forbidden (or any other appropriate status code)
                        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);

                        // Set the response content type to JSON
                        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

                        // Write the JSON error response to the response body
                        httpServletResponse.getWriter().write(jsonResponse);

                        // Stop the filter chain
                        return;
                    }
                }
            }

            String authorizationHeaderUserType = httpServletRequest.getHeader("UserType");

            if (authorizationHeaderUserType != null) {
                String decrypt = Encryption.decrypt(authorizationHeaderUserType);
                JSONParser parser = new JSONParser();
                try {
                    JSONObject json = (JSONObject) parser.parse(decrypt);
                    if (json.get("accType").toString().equals("buyer")) {

                    } else {
                        throw new RuntimeException("Invalid Token");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            } else {
                throw new RuntimeException("No Token");
            }
        }

// Continue the filter chain
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

