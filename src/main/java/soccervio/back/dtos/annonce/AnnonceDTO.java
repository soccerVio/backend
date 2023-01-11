package soccervio.back.dtos.invitation;

import java.util.List;

import javax.validation.constraints.NotBlank;

public class InvitationDTO {


    @NotBlank
	private long invitantId;

    @NotBlank
	private List<Long> inviteIds ;


	@NotBlank
	private long annonceId;

	public long getInvitantId() {
		return invitantId;
	}

	public List<Long> getInviteIds() {
		return inviteIds;
	}

	public long getAnnonceId() {
		return annonceId;
	}
}
