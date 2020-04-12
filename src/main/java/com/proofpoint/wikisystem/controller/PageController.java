package com.proofpoint.wikisystem.controller;

import com.proofpoint.wikisystem.model.Page;
import com.proofpoint.wikisystem.payload.CreatePageArgs;
import com.proofpoint.wikisystem.service.PageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/wikisystem/page")
public class PageController {

    @Autowired
    private PageService pageService;

    @RequestMapping(method = RequestMethod.POST,
            consumes = "application/json")
    public String create(@RequestBody final CreatePageArgs payload) {

        try {
            log.info("Received request to create page");
            log.info("Payload:"+payload.toString());
            pageService.create(payload.getPageId(), payload.getParentPageId());
            return "success";
        } catch (Exception e) {
            log.error(e.getMessage());
            return e.getMessage();
        }

    }

    @RequestMapping(method = RequestMethod.GET,
            produces = "application/json")
    public Page read(@RequestParam String pageId) {
        log.info("Received request to read page");
        return pageService.read(pageId);
    }

    @RequestMapping(method = RequestMethod.DELETE,
            produces = "application/json")
    public String delete(@RequestParam String pageId) {
        log.info("Received request to delete page");
        return pageService.delete(pageId);
    }
}
