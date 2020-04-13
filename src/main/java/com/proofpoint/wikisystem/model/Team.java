package com.proofpoint.wikisystem.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Team extends Collaborator {

    private boolean isAdmin;
    //add team members list

    private Team(Builder builder) {
        this.Id = builder.Id;
        this.isAdmin = builder.isAdmin;
    }

    @Override
    public String toString() {
        return "Team{" +
                "isAdmin=" + isAdmin +
                ", Id='" + Id + '\'' +
                '}';
    }

    public void create() {
        System.out.println("Creating team");
    }

    public void delete() {
        System.out.println("Deleting team");
    }

    public void update() {
        System.out.println("Updating team");
    }

    public static class Builder {
        private String Id;
        private boolean isAdmin;

        private Builder() {
        }

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder withID(String Id) {
            this.Id = Id;
            return this;
        }

        public Builder withIsAdmin(boolean isAdmin) {
            this.isAdmin = isAdmin;
            return this;
        }

        public Team build() {
            return new Team(this);
        }

    }
}
