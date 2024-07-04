package kg.aidar.fin_tech_innovators.dto;

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
public class AuthorizeUserDto {
    @NotEmpty(message = "username can not be empty")
    String username;

    @NotEmpty(message = "password can not be empty")
    String password;
}
