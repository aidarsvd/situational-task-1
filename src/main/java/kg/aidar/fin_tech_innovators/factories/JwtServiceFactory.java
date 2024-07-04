package kg.aidar.fin_tech_innovators.factories;


import kg.aidar.fin_tech_innovators.enums.JwtServiceChannel;
import kg.aidar.fin_tech_innovators.services.jwt.JwtService;

public interface JwtServiceFactory {
    JwtService defineJwtService(JwtServiceChannel jwtServiceChannel);
}
