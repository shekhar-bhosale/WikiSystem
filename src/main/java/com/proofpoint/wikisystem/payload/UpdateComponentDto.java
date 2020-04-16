package com.proofpoint.wikisystem.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateComponentDto {
    private String contents;
    private String ownerId;
    private String collaboratorId;
    private String isPage;
    private String isIndividualUser;
    private String requesterId;
}
