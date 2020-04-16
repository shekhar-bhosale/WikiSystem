package com.proofpoint.wikisystem.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDto {
    private String userId;
    private String userName;

    @Override
    public String toString() {
        return "CreateUserArgs{" +
                "ID='" + userId + '\'' +
                ", username='" + userName + '\'' +
                '}';
    }
}
