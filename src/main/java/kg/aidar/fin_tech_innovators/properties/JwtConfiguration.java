package kg.aidar.fin_tech_innovators.properties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
@ConfigurationProperties(prefix = "app.security.jwt")
public class JwtConfiguration {

    String secretKey;

    long accessTokenLife;

    long refreshTokenLife;

}