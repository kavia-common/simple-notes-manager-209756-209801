package com.example.notesbackend;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Fallback controller to ensure unmapped paths return 404 JSON rather than 500.
 * This avoids accidental static-resource exceptions surfacing as 500s.
 * Paths for API, actuator, swagger-ui, openapi, and h2-console are ignored.
 */
@RestController
@Hidden // Do not include in OpenAPI
public class NotFoundFallbackController {

    private static final AntPathMatcher PM = new AntPathMatcher();

    // PUBLIC_INTERFACE
    @RequestMapping("/**")
    public ResponseEntity<?> notFound(HttpServletRequest request) {
        String path = request.getRequestURI();

        // Allowlist: skip handling for these (let real handlers/static serve them)
        if (startsWithAny(path,
                "/api/", "/actuator", "/actuator/health", "/actuator/health/readiness", "/health", "/h2-console",
                "/swagger-ui", "/swagger-ui/index.html", "/openapi.json")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("path", path);
        body.put("message", "No handler found for " + path);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    private boolean startsWithAny(String path, String... prefixes) {
        if (path == null) return false;
        for (String p : prefixes) {
            if (path.equals(p) || path.startsWith(p)) {
                return true;
            }
        }
        return false;
    }
}
