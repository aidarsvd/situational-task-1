package kg.aidar.fin_tech_innovators.services.security.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import kg.aidar.fin_tech_innovators.dto.AuthorizeUserDto;
import kg.aidar.fin_tech_innovators.dto.AuthorizedUserDto;
import kg.aidar.fin_tech_innovators.enums.JwtServiceChannel;
import kg.aidar.fin_tech_innovators.exceptions.ApiException;
import kg.aidar.fin_tech_innovators.factories.JwtServiceFactory;
import kg.aidar.fin_tech_innovators.services.security.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final AuthenticationManager authenticationManager;
    private final JwtServiceFactory jwtServiceFactory;

    @Override
    public AuthorizedUserDto authorizeUser(AuthorizeUserDto authorizeUserDto) {
        log.info("Authorizing user {}", authorizeUserDto.getUsername());
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authorizeUserDto.getUsername(), authorizeUserDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String accessToken = jwtServiceFactory.defineJwtService(JwtServiceChannel.ACCESS).generateJwtToken(authorizeUserDto.getUsername());
        String refreshToken = jwtServiceFactory.defineJwtService(JwtServiceChannel.REFRESH).generateJwtToken(authorizeUserDto.getUsername());
        return AuthorizedUserDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthorizedUserDto refreshToken(String refreshToken) {
        try {
            DecodedJWT decodedJWT = jwtServiceFactory.defineJwtService(JwtServiceChannel.REFRESH).decodeAndValidateToken(refreshToken);
            String username = decodedJWT.getSubject();
            log.info("Refreshing token for user {}", username);
            String accessToken = jwtServiceFactory.defineJwtService(JwtServiceChannel.ACCESS).generateJwtToken(username);
            String refreshedToken = jwtServiceFactory.defineJwtService(JwtServiceChannel.REFRESH).generateJwtToken(username);
            return AuthorizedUserDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshedToken)
                    .build();
        } catch (Exception e) {
            throw new ApiException("Token is not relevant");
        }
    }
}
