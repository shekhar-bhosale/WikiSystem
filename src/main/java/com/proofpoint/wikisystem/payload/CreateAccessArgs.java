package com.proofpoint.wikisystem.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccessArgs {
    private String componentId;
    private String accessType;
    private String collaboratorId;
    private boolean isPage;
    private boolean isIndividualUser;

    @Override
    public String toString() {
        return "CreateAccessArgs{" +
                "componentId='" + componentId + '\'' +
                ", accesstype='" + accessType + '\'' +
                ", collaboratorId='" + collaboratorId + '\'' +
                ", isPage=" + isPage +
                ", isIndividualUser=" + isIndividualUser +
                '}';
    }
}
