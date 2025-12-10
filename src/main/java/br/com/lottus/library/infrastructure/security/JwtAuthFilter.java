package br.com.lottus.library.infrastructure.security;

import br.com.lottus.library.application.ports.out.JwtProviderPort;
import br.com.lottus.library.application.ports.out.TokenBlocklistPort;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtProviderPort jwtProvider;
    private final TokenBlocklistPort tokenBlocklist;
    private final UserDetailsService userDetailsService;
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);


    private static final List<String> IGNORED_PATHS = Arrays.asList(
            "/api/auth/login",
            "/api/auth/register",
            "/swagger-ui/",
            "/v3/api-docs"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        logger.info("=== STARTING JWT FILTER FOR URL: {} ===", request.getRequestURI());

        if (isIgnored(request)) {
            logger.info("Path is ignored, skipping filter.");
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");
        logger.info("Authorization header: {}", authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            logger.warn("Authorization header is missing or does not start with Bearer. Continuing filter chain.");
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authHeader.substring(7);
        logger.info("Extracted token: {}", token);

        try {
            final String jti = jwtProvider.getJTIFromToken(token);
            logger.info("Extracted JTI: {}", jti);

            if (tokenBlocklist.isBlocked(jti)) {
                logger.warn("Token is blocklisted. JTI: {}", jti);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            final String userEmail = jwtProvider.getEmailFromToken(token);
            logger.info("Extracted email from token: {}", userEmail);

            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                logger.info("Loading user details for email: {}", userEmail);
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                logger.info("Validating token...");
                if (jwtProvider.isTokenValid(token, userDetails.getUsername())) {
                    logger.info("Token is valid for user: {}", userEmail);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    logger.info("Authentication set in SecurityContextHolder successfully.");
                } else {
                    logger.warn("Token is invalid for user: {}", userEmail);
                }
            } else {
                if (userEmail == null) {
                    logger.warn("Email not found in token.");
                }
                if (SecurityContextHolder.getContext().getAuthentication() != null) {
                    logger.info("User is already authenticated.");
                }
            }
        } catch (MalformedJwtException | ExpiredJwtException e) {
            logger.error("Error processing JWT: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        logger.info("=== FINISHING JWT FILTER ===");
        filterChain.doFilter(request, response);
    }

    private boolean isIgnored(HttpServletRequest request) {
        String path = request.getRequestURI();
        return IGNORED_PATHS.stream().anyMatch(path::startsWith);
    }
}
