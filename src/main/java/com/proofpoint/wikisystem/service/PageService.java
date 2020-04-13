package com.proofpoint.wikisystem.service;

import com.proofpoint.wikisystem.model.Page;
import com.proofpoint.wikisystem.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@Scope("singleton")
public class PageService {

    private Map<String, Page> pages = new HashMap<>();

    public void create(String pageID, String parentPageID, User owner) {
        log.info("Creating page with pageId:" + pageID);
        Page page = Page
                .PageBuilder
                .newInstance()
                .withPageID(pageID)
                .withParentPageID(parentPageID)
                .withOwner(owner)
                .build();
        log.info("Page created:" + page.toString());
        pages.put(pageID, page);

    }

    public Page read(String pageID) {
        if (pages.containsKey(pageID)) {
            return pages.get(pageID);
        } else {
            return null;
        }
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
