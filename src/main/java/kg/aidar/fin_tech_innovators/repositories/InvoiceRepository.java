package kg.aidar.fin_tech_innovators.repositories;

import kg.aidar.fin_tech_innovators.entities.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
}
