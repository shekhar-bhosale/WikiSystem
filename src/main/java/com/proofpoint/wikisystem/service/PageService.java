package com.proofpoint.wikisystem.service;

import com.proofpoint.wikisystem.entities.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageService {

    private List<Page> pages;

    public void createPage(String pageID, String parentPageID){
        //Builder pattern in lombok not working
        Page page = Page
                .PageBuilder
                .newInstance()
                .withPageID(pageID)
                .withParentPageID(parentPageID)
                .build();
        pages.add(page);
    }
}
