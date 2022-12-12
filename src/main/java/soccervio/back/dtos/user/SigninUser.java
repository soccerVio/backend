package soccervio.back.dtos.user;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SigninUser {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
