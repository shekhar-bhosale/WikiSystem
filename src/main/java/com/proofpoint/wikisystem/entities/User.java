package com.proofpoint.wikisystem.entities;

public class User extends Collaborator {
    private String userID;

    public void create(){
        System.out.println("Creating User");
    }

    public void delete(){
        System.out.println("Deleting User");
    }

    public void update(){
        System.out.println("Updating User");
    }
}
