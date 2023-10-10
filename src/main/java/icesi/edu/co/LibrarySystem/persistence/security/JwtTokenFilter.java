package icesi.edu.co.LibrarySystem.persistence.security;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.algorithms.Algorithm;
import com.nimbusds.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Get the bearer token from the request header
        String bearerToken = request.getHeader(AUTHORIZATION);

        // Validate the token
        DecodedJWT decodedJwt = jwtTokenProvider.validateToken(bearerToken);

        // Set the user in the security context
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        decodedJwt.getSubject(),
                        null,
                        decodedJwt.getClaims().get("authorities")
                )
        );

        filterChain.doFilter(request, response);
    }
}
