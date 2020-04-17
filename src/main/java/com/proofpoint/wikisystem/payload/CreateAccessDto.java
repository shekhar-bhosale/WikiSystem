package com.proofpoint.wikisystem.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccessDto {
    private String componentId;
    private String accessType;
    private String collaboratorId;
    private String isPage;
    private String isIndividualUser;

    @Override
    public String toString() {
        return "CreateAccessDto{" +
                "componentId='" + componentId + '\'' +
                ", accessType='" + accessType + '\'' +
                ", collaboratorId='" + collaboratorId + '\'' +
                ", isPage='" + isPage + '\'' +
                ", isIndividualUser='" + isIndividualUser + '\'' +
                '}';
    }
}
