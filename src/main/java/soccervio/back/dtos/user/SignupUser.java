package soccervio.back.dtos.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import soccervio.back.entities.enums.Gender;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class SignupUser {

    @NotBlank
    private String fullname;

    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

    private Gender gender;

    @NotBlank
    private String password;

    private String type;
}
