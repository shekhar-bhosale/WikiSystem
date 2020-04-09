package com.proofpoint.wikisystem.entities;

public abstract class Component {
    protected User owner;
    public abstract void create();
    public abstract void delete();
    public abstract void update();
}
