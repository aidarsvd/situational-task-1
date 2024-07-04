package kg.aidar.fin_tech_innovators.services.security;


import kg.aidar.fin_tech_innovators.dto.AuthorizeUserDto;
import kg.aidar.fin_tech_innovators.dto.AuthorizedUserDto;

public interface SecurityService {

    AuthorizedUserDto authorizeUser(AuthorizeUserDto authorizeUserDto);

    AuthorizedUserDto refreshToken(String refreshToken);

}
