package icesi.edu.co.LibrarySystem.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    private String secretKey = "tu-secreto"; // Debe ser una clave segura y no debe revelarse
    private long validityInMilliseconds = 3600000; // 1 hora

    public String createToken(String username, List<String> roles) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

    }

    // Agregar manejo de excepciones
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Implementa lógica para obtener los roles del usuario
    public List<String> getUserRoles(String username) {
        // Supongamos una lista de roles predefinidos
        List<String> roles = new ArrayList<>();

        // Aquí puedes implementar la lógica para buscar los roles del usuario en tu base de datos o sistema de autenticación
        if ("admin".equals(username)) {
            // Si el usuario es "admin", asigna el rol "ADMIN"
            roles.add("ROLE_ADMIN");
        } else {
            // De lo contrario, asigna el rol "USER"
            roles.add("ROLE_USER");
        }

        return roles;
    }
}
