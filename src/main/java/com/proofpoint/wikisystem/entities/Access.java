package com.proofpoint.wikisystem.entities;

import java.util.List;
import java.util.Map;

public class Access {
    private Component component;
    private AccessType accessType;
    private Map<AccessType, List<Collaborator>> accessMap;

}
