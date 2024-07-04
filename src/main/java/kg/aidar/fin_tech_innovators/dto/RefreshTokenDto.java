package kg.aidar.fin_tech_innovators.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshTokenDto {

    @NotEmpty(message = "refresh token can not be null")
    @JsonProperty("refresh_token")
    String refreshToken;

}