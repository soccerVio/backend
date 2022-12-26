package soccervio.back.dtos.user;


import javax.validation.constraints.NotBlank;

public class SigninUser {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public SigninUser() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
