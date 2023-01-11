package soccervio.back.dtos.annoce;

import javax.validation.constraints.NotBlank;

public class AnnonceDTO {

    @NotBlank
    private String description;

    @NotBlank
    private long idReservation;

    public String getDescription() {
        return description;
    }

    public long getIdReservation() {
        return idReservation;
    }
}
