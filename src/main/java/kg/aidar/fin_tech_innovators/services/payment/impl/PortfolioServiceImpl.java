package kg.aidar.fin_tech_innovators.services.payment.impl;

import kg.aidar.fin_tech_innovators.dto.AccountCreateDto;
import kg.aidar.fin_tech_innovators.dto.PortfolioDto;
import kg.aidar.fin_tech_innovators.entities.PortfolioEntity;
import kg.aidar.fin_tech_innovators.entities.UserEntity;
import kg.aidar.fin_tech_innovators.models.AppUserDetails;
import kg.aidar.fin_tech_innovators.repositories.PortfolioRepository;
import kg.aidar.fin_tech_innovators.repositories.UserRepository;
import kg.aidar.fin_tech_innovators.services.payment.PortfolioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final UserRepository userRepository;
    private final PortfolioRepository portfolioRepository;

    @Override
    public PortfolioDto createPortfolio(AccountCreateDto accountCreateDto) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        UserEntity user = userRepository.findUserByUsername(((AppUserDetails) authentication.getPrincipal()).getUsername()).orElseThrow();
        PortfolioEntity entity = new PortfolioEntity(null, user, accountCreateDto.getRequisite(), BigDecimal.ZERO);
        PortfolioEntity portfolioEntity = portfolioRepository.save(entity);
        return PortfolioDto.builder()
                .id(portfolioEntity.getId())
                .balance(portfolioEntity.getBalance())
                .requisite(portfolioEntity.getRequisite())
                .build();
    }
}
