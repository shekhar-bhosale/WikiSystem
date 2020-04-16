package com.proofpoint.wikisystem.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateTeamDto {
    private String teamId;
    private boolean isAdmin;

    @Override
    public String toString() {
        return "CreateTeamArgs{" +
                "teamId='" + teamId + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
