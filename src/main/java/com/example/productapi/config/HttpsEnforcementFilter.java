package com.example.productapi.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class HttpsEnforcementFilter extends OncePerRequestFilter {
    @Value("${app.security.enforce-https:false}")
    private boolean enforceHttps;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (enforceHttps && !isHttps(request)) {
            String target = "https://" + request.getServerName()
                    + (request.getServerPort() == 80 || request.getServerPort() == 443 ? "" : ":" + request.getServerPort())
                    + request.getRequestURI()
                    + (request.getQueryString() == null ? "" : "?" + request.getQueryString());
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            response.setHeader("Location", target);
            return;
        }
        chain.doFilter(request, response);
    }

    private boolean isHttps(HttpServletRequest request) {
        String forwarded = request.getHeader("X-Forwarded-Proto");
        return request.isSecure() || "https".equalsIgnoreCase(forwarded);
    }
}
