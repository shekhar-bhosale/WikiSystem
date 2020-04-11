package com.proofpoint.wikisystem.service;

import com.proofpoint.wikisystem.entities.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service @Slf4j
public class PageService {

    private Map<String,Page> pages = new HashMap<>();

    public void createPage(String pageID, String parentPageID){
        log.info("Creating page with pageId:"+pageID);
        /*Page page = Page
                .builder()
                .pageID(pageID)
                .parentPageID(parentPageID)
                .build();*/

        Page page = Page
                .PageBuilder
                .newInstance()
                .withPageID(pageID)
                .withParentPageID(parentPageID)
                .build();
        log.info("Page created:"+page.toString());
        pages.put(pageID, page);

    }

    public Page readPage(String pageID){
        if(pages.containsKey(pageID)){
            return pages.get(pageID);
        }else{
            return null;
        }
    }


}
