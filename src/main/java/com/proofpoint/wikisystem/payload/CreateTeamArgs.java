package com.proofpoint.wikisystem.payload;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CreateTeamArgs {
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
