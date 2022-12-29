package soccervio.back.dtos.user;

import soccervio.back.entities.Image;

import soccervio.back.entities.Column;

public class UserDTO {
	@NoyBlank
    private String username;

    @NotBlank
    private String password;
    @NotBlank
    private String nomComplet;
    @NotBlank
    private String email;
    @NotBlank
    private String numTel;
    @NotBlank
    private Image image;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNomComplet() {
		return nomComplet;
	}
	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumTel() {
		return numTel;
	}
	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}


}
