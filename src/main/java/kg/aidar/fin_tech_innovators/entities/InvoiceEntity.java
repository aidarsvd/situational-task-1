package kg.aidar.fin_tech_innovators.entities;

import jakarta.persistence.*;
import kg.aidar.fin_tech_innovators.enums.InvoiceStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    UUID receipt;

    LocalDateTime createdAt;

    @OneToOne
    PortfolioEntity sender;

    @OneToOne
    PortfolioEntity recipient;

    BigDecimal amount;

    @Enumerated(EnumType.STRING)
    InvoiceStatus status;

}
