package kg.aidar.fin_tech_innovators.controllers;

import jakarta.validation.Valid;
import kg.aidar.fin_tech_innovators.dto.*;
import kg.aidar.fin_tech_innovators.services.security.SecurityService;
import kg.aidar.fin_tech_innovators.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trusted/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final SecurityService securityService;

    @PostMapping("/sign-up")
    public SuccessDto signUp(@Valid @RequestBody SignUpUserDto signUpUserDto) {
        userService.saveUser(signUpUserDto);
        return SuccessDto.builder().build();
    }

    @PostMapping("/sign-in")
    public AuthorizedUserDto signInUser(@Valid @RequestBody AuthorizeUserDto authorizeUserDto) {
        return securityService.authorizeUser(authorizeUserDto);
    }

    @PostMapping("/refresh-token")
    public AuthorizedUserDto refreshToken(@Valid @RequestBody RefreshTokenDto refreshTokenDto) {
        return securityService.refreshToken(refreshTokenDto.getRefreshToken());
    }

}