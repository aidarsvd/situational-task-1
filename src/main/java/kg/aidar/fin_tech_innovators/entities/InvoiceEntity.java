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

    @ManyToOne
    PortfolioEntity sender;

    @ManyToOne
    PortfolioEntity recipient;

    BigDecimal amount;

    @Enumerated(EnumType.STRING)
    InvoiceStatus status;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.receipt = UUID.randomUUID();
    }


    public InvoiceEntity(BigDecimal amount) {
        this.amount = amount;
    }
}
