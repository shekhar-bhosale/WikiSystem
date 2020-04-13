package com.proofpoint.wikisystem.service;

import com.proofpoint.wikisystem.model.Team;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@Scope("singleton")
public class TeamService {

    private Map<String, Team> teams = new HashMap<>();

    public void create(String ID, boolean isAdmin) {
        log.info("Creating team with team:" + ID);
        Team team = Team.Builder
                .newInstance()
                .withID(ID)
                .withIsAdmin(isAdmin)
                .build();
        log.info("Team created:" + team.toString());
        teams.put(ID, team);

    }

    public Team read(String teamID) {
        if (teams.containsKey(teamID)) {
            Team output = teams.get(teamID);
            log.info("Team found:" + output.toString());
            return output;
        } else {
            return null;
        }
    }

    public String delete(String teamId) {
        if (teams.containsKey(teamId)) {
            teams.remove(teamId);
            return "Team deleted successfully";
        } else {
            return "Team not found";
        }
    }
}
