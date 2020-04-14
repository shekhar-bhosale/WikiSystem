package com.proofpoint.wikisystem.service;

import com.proofpoint.wikisystem.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@Scope("singleton")
public class UserService {

    private Map<String, User> users = new HashMap<>();

    public void create(String ID, String username) {
        log.info("Creating user with userID:" + ID);
        User user = User.Builder
                .newInstance()
                .withID(ID)
                .withUsername(username)
                .build();
        log.info("User created:" + user.toString());
        users.put(ID, user);

    }

    public User read(String userID) {
        log.info("Checking if user "+userID+" exists in system.");
        return users.getOrDefault(userID, null);
    }

    public String delete(String userId) {
        if (users.containsKey(userId)) {
            users.remove(userId);
            return "User deleted successfully";
        } else {
            return "User not found";
        }
    }
}
