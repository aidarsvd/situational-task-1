package kg.aidar.fin_tech_innovators.services.jwt.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import kg.aidar.fin_tech_innovators.enums.JwtServiceChannel;
import kg.aidar.fin_tech_innovators.properties.JwtConfiguration;
import kg.aidar.fin_tech_innovators.services.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshTokenService implements JwtService {

    private final JwtConfiguration jwtConfiguration;

    @Override
    public String generateJwtToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtConfiguration.getRefreshTokenLife()))
                .sign(Algorithm.HMAC256(jwtConfiguration.getSecretKey().getBytes()));
    }

    @Override
    public DecodedJWT decodeAndValidateToken(String token) throws Exception {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(jwtConfiguration.getSecretKey().getBytes())).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        if (decodedJWT.getExpiresAt().getTime() <= System.currentTimeMillis()) throw new Exception("Token is expired");
        return decodedJWT;
    }

    @Override
    public JwtServiceChannel defineChannel() {
        return JwtServiceChannel.REFRESH;
    }
}
