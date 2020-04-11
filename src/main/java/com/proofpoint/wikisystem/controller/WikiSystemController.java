package com.proofpoint.wikisystem.controller;

import com.proofpoint.wikisystem.arguments.CreatePageArgs;
import com.proofpoint.wikisystem.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@RestController
@RequestMapping("/wikisystem")
public class WikiSystemController {

    @Autowired
    PageService pageService;

    private static Logger logger = LoggerFactory.getLogger(WikiSystemController.class.getName());

    public static Logger getLogger() {
        return logger;
    }

    @RequestMapping(value = "/create",
            method = RequestMethod.POST,
            consumes = "application/json")
    public String create(@RequestBody final CreatePageArgs payload) {

        try {
//            pageService.createPage();
            return "SUCCESS";
        } catch (Exception e) {
            logger.error(e.getMessage());
            return e.getMessage();
        }

    }
}
