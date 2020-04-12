package com.proofpoint.wikisystem.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Team extends Collaborator {

    private boolean isAdmin;

    private Team(Builder builder){
        this.Id = builder.ID;
        this.isAdmin = builder.isAdmin;
    }

    public static class Builder{
        private String ID;
        private boolean isAdmin;

        public static Builder newInstance(){
            return new Builder();
        }

        private Builder() {}

        public Builder withID(String ID){
            this.ID = ID;
            return this;
        }

        public Builder withIsAdmin(boolean isAdmin){
            this.isAdmin = isAdmin;
            return this;
        }

        public Team build(){
            return new Team(this);
        }

    }

    @Override
    public String toString() {
        return "Team{" +
                "isAdmin=" + isAdmin +
                ", Id='" + Id + '\'' +
                '}';
    }

    public void create(){
        System.out.println("Creating team");
    }

    public void delete(){
        System.out.println("Deleting team");
    }

    public void update(){
        System.out.println("Updating team");
    }
}
