package com.proofpoint.wikisystem.controller;

import com.proofpoint.wikisystem.model.Page;
import com.proofpoint.wikisystem.model.User;
import com.proofpoint.wikisystem.payload.CreatePageArgs;
import com.proofpoint.wikisystem.service.PageService;
import com.proofpoint.wikisystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.proofpoint.wikisystem.util.Constants.STATUS_SUCCESS;

@Slf4j
@RestController
@RequestMapping("/wikisystem/page")
public class PageController {

    @Autowired
    private PageService pageService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public String create(@RequestBody final CreatePageArgs payload) {

        try {
            log.info("Received request to create page");
            log.info("Payload:" + payload.toString());

            User owner = userService.read(payload.getOwnerId());
            pageService.create(payload.getPageId(), payload.getParentPageId(), owner);

            //TODO: Construct Response
            return STATUS_SUCCESS;

        } catch (final Exception e) {
            log.error(e.getMessage());
            //TODO: Construct Response
            return e.getMessage();
        }

    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public Page read(@RequestParam final String pageId) {
        log.info("Received request to read page");
        return pageService.read(pageId);
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
    public String delete(@RequestParam final String pageId) {
        log.info("Received request to delete page");
        return pageService.delete(pageId);
    }
}
