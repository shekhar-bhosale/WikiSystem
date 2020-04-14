package com.proofpoint.wikisystem.service;

import com.proofpoint.wikisystem.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@Scope("singleton")
public class PageService {

    private Map<String, Page> pages = new HashMap<>();

    @Autowired
    private AccessService accessService;

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    public void create(String pageID, String parentPageID, User owner, String content, Map<String, String> accessMap) throws Exception {
        log.info("Creating page with pageId:" + pageID);
        Page page = Page
                .PageBuilder
                .newInstance()
                .withPageID(pageID)
                .withParentPageID(parentPageID)
                .withOwner(owner)
                .withContent(content)
                .build();

        if(accessMap!=null){
            log.info("Assigning access rights to component");
            for(String collaboratorId: accessMap.keySet()){
                Collaborator collaborator;
                collaborator = userService.read(collaboratorId);
                if(collaborator==null){
                    collaborator = teamService.read(collaboratorId);
                    if(collaborator==null) {
                        log.error("User does not exist");
                        throw new Exception("Given user in access map does not exist");
                    }
                }
                accessService.assignAccess(page, AccessType.valueOf(accessMap.get(collaboratorId)), collaborator);
            }
        }
        log.info("Page created:" + page.toString());
        pages.put(pageID, page);

    }

    public Page read(String pageID) {
        return pages.getOrDefault(pageID, null);
    }

    public String delete(String pageID) {
        if (pages.containsKey(pageID)) {
            pages.remove(pageID);
            return "Page deleted successfully";
        } else {
            return "Page not found";
        }
    }


}
