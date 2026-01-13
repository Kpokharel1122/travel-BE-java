package com.onsyatra_backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class ApiKeyAuthFilter extends OncePerRequestFilter {

    @Value("${security.api.key}")
    private String apiKey;

    private static final String HEADER_NAME = "Authorization";

    // Public endpoints that should skip API key check
    private static final List<String> PUBLIC_PATHS = List.of(
            "/contact", "/public", "/health", "/actuator"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Remove context path for correct matching
        String path = request.getRequestURI().substring(request.getContextPath().length());

        // Skip API key check for public endpoints
        boolean isPublic = PUBLIC_PATHS.stream().anyMatch(path::startsWith);
        if (isPublic) {
            filterChain.doFilter(request, response);
            return;
        }

        // Existing API key check
        String headerValue = request.getHeader(HEADER_NAME);

        System.out.println("[ApiKeyAuthFilter] Incoming Authorization header: " + headerValue);
        System.out.println("[ApiKeyAuthFilter] Expected API Key: " + apiKey);

        if (headerValue == null || headerValue.isEmpty()) {
            respondForbidden(response, "Missing Authorization header");
            return;
        }

        if (!apiKey.equals(headerValue.trim())) {
            respondForbidden(response, "Invalid API Key");
            return;
        }

        // Set authentication so Spring Security treats this request as authenticated
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                "apiKeyUser", null, Collections.emptyList()
        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }

    private void respondForbidden(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.getWriter().write("""
            {"success":false,"status":403,"message":"%s"}
            """.formatted(message));
    }
}
