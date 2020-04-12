package com.proofpoint.wikisystem.service;

import com.proofpoint.wikisystem.model.AccessType;
import com.proofpoint.wikisystem.model.Collaborator;
import com.proofpoint.wikisystem.model.Component;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Scope("singleton")
public class AccessService {

    public void assignAccess(Component component, AccessType accessType, Collaborator collaborator){
        Map<AccessType, List<Collaborator>> accessMap = component.getAccessMap();
        accessMap.get(accessType).add(collaborator);
    }
}
