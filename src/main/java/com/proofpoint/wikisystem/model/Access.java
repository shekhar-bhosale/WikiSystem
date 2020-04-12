package com.proofpoint.wikisystem.model;

import java.util.List;
import java.util.Map;

public class Access {
    private Component component;
    private AccessType accessType;
    private Map<AccessType, List<Collaborator>> accessMap;

}
