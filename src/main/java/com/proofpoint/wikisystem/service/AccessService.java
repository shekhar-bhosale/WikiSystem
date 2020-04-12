package com.proofpoint.wikisystem.service;

import com.proofpoint.wikisystem.model.AccessType;
import com.proofpoint.wikisystem.model.Collaborator;
import com.proofpoint.wikisystem.model.Component;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Scope("singleton")
@Slf4j
public class AccessService {

    public void assignAccess(Component component, AccessType accessType, Collaborator collaborator){
        Map<AccessType, List<Collaborator>> accessMap = component.getAccessMap();
//        log.info("Access map received:"+accessMap.toString());
        if(accessMap.containsKey(accessType)){
            accessMap.get(accessType).add(collaborator);
        }else{
            List<Collaborator> list = new ArrayList<>();
            list.add(collaborator);
            accessMap.put(accessType,list);
        }
    }
}
