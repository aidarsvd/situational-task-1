package kg.aidar.fin_tech_innovators.controllers;

import kg.aidar.fin_tech_innovators.dto.AccountCreateDto;
import kg.aidar.fin_tech_innovators.dto.PortfolioDto;
import kg.aidar.fin_tech_innovators.dto.UserDto;
import kg.aidar.fin_tech_innovators.services.payment.PortfolioService;
import kg.aidar.fin_tech_innovators.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PortfolioService portfolioService;

    @GetMapping("/profile")
    ResponseEntity<UserDto> getProfile() {
        return ResponseEntity.ok(userService.getProfile());
    }

    @PostMapping("/create-account")
    ResponseEntity<PortfolioDto> createAccount(@RequestBody AccountCreateDto accountCreateDto) {
        return ResponseEntity.ok(portfolioService.createPortfolio(accountCreateDto));
    }

}
