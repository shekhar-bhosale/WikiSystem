package com.proofpoint.wikisystem.entities;

public class Team extends Collaborator {

    private String teamID;
    private boolean isAdmin;

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
