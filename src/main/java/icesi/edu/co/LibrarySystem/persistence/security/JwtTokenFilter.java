package icesi.edu.co.LibrarySystem.persistence.security;

import com.google.auth.oauth2.JwtClaims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(1)
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Get the token from the request
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            // Remove the Bearer prefix
            token = token.substring(7);

            try {
                // Validate the token
                JwtClaims claims = jwtTokenProvider.parseToken(token);

                // Add the claims to the security context
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        claims.getSubject(), null, claims.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } catch (Exception e) {
                // Ignore invalid tokens
            }
        }

        chain.doFilter(request, response);
    }

}
