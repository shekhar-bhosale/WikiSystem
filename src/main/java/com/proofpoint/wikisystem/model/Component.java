package com.proofpoint.wikisystem.model;

import java.util.List;
import java.util.Map;

public abstract class Component implements Operations{
    protected User owner;
    protected Map<AccessType, List<Collaborator>> accessMap;

    public Map<AccessType, List<Collaborator>> getAccessMap(){
        return this.accessMap;
    }
}
