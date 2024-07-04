package kg.aidar.fin_tech_innovators.services.user;

import kg.aidar.fin_tech_innovators.dto.SignUpUserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void saveUser(SignUpUserDto signUpUserDto);

    boolean isUserExists(String username);

}
