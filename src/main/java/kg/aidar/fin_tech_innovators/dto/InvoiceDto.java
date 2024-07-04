package kg.aidar.fin_tech_innovators.dto;

import kg.aidar.fin_tech_innovators.enums.InvoiceStatus;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceDto {

    PortfolioDto sender;

    PortfolioDto recipient;

    BigDecimal amount;

    UUID receipt;

    LocalDateTime createdAt;

    Long id;

    InvoiceStatus status;

}
