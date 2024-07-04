package kg.aidar.fin_tech_innovators.services.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import kg.aidar.fin_tech_innovators.enums.JwtServiceChannel;

public interface JwtService {

    String generateJwtToken(String username);

    DecodedJWT decodeAndValidateToken(String token) throws Exception;

    JwtServiceChannel defineChannel();

}
