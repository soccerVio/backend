package soccervio.back.dtos.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SignupUser {

    @NotBlank
    private String nomComplet;

    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String typeCompte;

    public String getNomComplet() {
        return nomComplet;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getTypeCompte() {
        return typeCompte;
    }
}
