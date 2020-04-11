package com.proofpoint.wikisystem.controller;

import com.proofpoint.wikisystem.arguments.CreatePageArgs;
import com.proofpoint.wikisystem.service.PageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Slf4j
@RestController
@RequestMapping("/wikisystem")
public class WikiSystemController {

    @Autowired
    PageService pageService;

    @RequestMapping(value = "/createpage",
            method = RequestMethod.POST,
            consumes = "application/json")
    public String create(@RequestBody final CreatePageArgs payload) {

        try {
            pageService.createPage(payload.getPageId(), payload.getParentPageId());
            return "success";
        } catch (Exception e) {
            log.error(e.getMessage());
            return e.getMessage();
        }

    }

}
