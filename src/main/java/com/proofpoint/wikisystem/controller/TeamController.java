package com.proofpoint.wikisystem.controller;

import com.proofpoint.wikisystem.model.Team;
import com.proofpoint.wikisystem.payload.CreateTeamArgs;
import com.proofpoint.wikisystem.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/wikisystem/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @RequestMapping(method = RequestMethod.POST,
            consumes = "application/json")
    public String create(@RequestBody final CreateTeamArgs payload) {

        try {
            log.info("Received request to create team");
            log.info("Payload:"+payload.toString());
            teamService.create(payload.getTeamId(), payload.isAdmin());
            return "SUCEESS";
        } catch (Exception e) {
            log.error(e.getMessage());
            return e.getMessage();
        }

    }

    @RequestMapping(method = RequestMethod.GET,
            produces = "application/json")
    public Team read(@RequestParam String teamId) {
        log.info("Received request to read team");
        return teamService.read(teamId);
    }

    @RequestMapping(method = RequestMethod.DELETE,
            produces = "application/json")
    public String delete(@RequestParam String teamId) {
        log.info("Received request to delete team");
        return teamService.delete(teamId);
    }
}
