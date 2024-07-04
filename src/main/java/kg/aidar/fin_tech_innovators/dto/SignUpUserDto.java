package kg.aidar.fin_tech_innovators.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpUserDto {

    @NotEmpty(message = "username is required")
    @Size(min = 8, max = 200, message = "username should be min 8 and max 200 chars")
    String username;

    @NotEmpty(message = "password is required")
    @Size(min = 8, max = 200, message = "password should be min 8 and max 200 chars")
    String password;

    @NotEmpty(message = "name is required")
    String name;

}
