package kg.aidar.fin_tech_innovators.services.payment;

import kg.aidar.fin_tech_innovators.dto.AccountCreateDto;
import kg.aidar.fin_tech_innovators.dto.PortfolioDto;

public interface PortfolioService {

    PortfolioDto createPortfolio(AccountCreateDto accountCreateDto);

}
