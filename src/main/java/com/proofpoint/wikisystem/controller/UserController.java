package com.proofpoint.wikisystem.controller;

import com.proofpoint.wikisystem.model.User;
import com.proofpoint.wikisystem.payload.CreateUserArgs;
import com.proofpoint.wikisystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/wikisystem/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST,
            consumes = "application/json")
    public String create(@RequestBody final CreateUserArgs payload) {

        try {
            log.info("Received request to create user");
            log.info("Payload:"+payload.toString());
            userService.create(payload.getUserId(), payload.getUsername());
            return "SUCCESS";
        } catch (Exception e) {
            log.error(e.getMessage());
            return e.getMessage();
        }

    }

    @RequestMapping(method = RequestMethod.GET,
            produces = "application/json")
    public User read(@RequestParam String userID) {
        log.info("Received request to read user");
        return userService.read(userID);
    }

    @RequestMapping(method = RequestMethod.DELETE,
            produces = "application/json")
    public String delete(@RequestParam String userId) {
        log.info("Received request to delete user");
        return userService.delete(userId);
    }

}
