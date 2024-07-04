package kg.aidar.fin_tech_innovators.repositories;

import kg.aidar.fin_tech_innovators.entities.PortfolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<PortfolioEntity, Long> {
}
