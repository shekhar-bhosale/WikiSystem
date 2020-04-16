package com.proofpoint.wikisystem.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeletePageDto {
    private String requesterId;
    private String isIndividualUser;
}
