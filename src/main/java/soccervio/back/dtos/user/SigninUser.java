package soccervio.back.dtos.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class SigninUser {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
