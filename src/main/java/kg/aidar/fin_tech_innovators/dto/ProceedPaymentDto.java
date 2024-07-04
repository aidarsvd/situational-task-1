package kg.aidar.fin_tech_innovators.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProceedPaymentDto {

    @JsonProperty("recipient_requisite")
    String recipientRequisite;

    BigDecimal amount;

    @JsonProperty("account_id")
    Long accountId;

}
