package com.proofpoint.wikisystem.payload;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateUserArgs {
    private String userId;
    private String username;

    @Override
    public String toString() {
        return "CreateUserArgs{" +
                "ID='" + userId + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
