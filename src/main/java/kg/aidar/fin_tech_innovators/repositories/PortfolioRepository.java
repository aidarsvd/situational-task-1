package kg.aidar.fin_tech_innovators.repositories;

import kg.aidar.fin_tech_innovators.entities.PortfolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<PortfolioEntity, Long> {

    boolean existsByRequisite(String req);

    PortfolioEntity findByRequisite(String req);

}
