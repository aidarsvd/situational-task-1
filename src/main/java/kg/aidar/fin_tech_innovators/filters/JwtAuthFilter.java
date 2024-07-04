package kg.aidar.fin_tech_innovators.filters;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kg.aidar.fin_tech_innovators.enums.JwtServiceChannel;
import kg.aidar.fin_tech_innovators.factories.JwtServiceFactory;
import kg.aidar.fin_tech_innovators.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final JwtServiceFactory jwtServiceFactory;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String bearerToken = authHeader.substring("Bearer ".length());
        try {
            DecodedJWT decodedJWT = jwtServiceFactory.defineJwtService(JwtServiceChannel.ACCESS).decodeAndValidateToken(bearerToken);
            UserDetails userDetails = userService.loadUserByUsername(decodedJWT.getSubject());
            if (userDetails != null) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (TokenExpiredException e) {
            // Handle token expiration
            throw new RuntimeException("Token expired");
        } catch (Exception e) {
            // Handle other exceptions
            throw new RuntimeException("Token expired");
        }

        filterChain.doFilter(request, response);
    }
}
