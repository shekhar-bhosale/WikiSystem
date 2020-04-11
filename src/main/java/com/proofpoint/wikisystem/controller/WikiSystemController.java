package com.proofpoint.wikisystem.controller;

import com.proofpoint.wikisystem.entities.Attachment;
import com.proofpoint.wikisystem.payload.CreateAttachmentArgs;
import com.proofpoint.wikisystem.payload.CreatePageArgs;
import com.proofpoint.wikisystem.entities.Page;
import com.proofpoint.wikisystem.service.AttachmentService;
import com.proofpoint.wikisystem.service.PageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/wikisystem")
public class WikiSystemController {

    @Autowired
    private PageService pageService;

    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping(value = "/createpage",
            method = RequestMethod.POST,
            consumes = "application/json")
    public String create(@RequestBody final CreatePageArgs payload) {

        try {
            log.info("Received request to create page");
            log.info("Payload:"+payload.toString());
            pageService.createPage(payload.getPageId(), payload.getParentPageId());
            return "success";
        } catch (Exception e) {
            log.error(e.getMessage());
            return e.getMessage();
        }

    }

    @RequestMapping(value = "/readpage",
            method = RequestMethod.GET,
            produces = "application/json")
    public Page read(@RequestParam String pageId) {
        log.info("Received request to read page");
        return pageService.readPage(pageId);
    }

    @RequestMapping(value = "/createfile",
            method = RequestMethod.POST,
            consumes = "application/json")
    public String create(@RequestBody final CreateAttachmentArgs payload) {

        try {
            log.info("Received request to create attachment");
            log.info("Payload:"+payload.toString());
            attachmentService.createAttachment(payload.getFilename(), payload.getContents());
            return "SUCEESS";
        } catch (Exception e) {
            log.error(e.getMessage());
            return e.getMessage();
        }

    }

    @RequestMapping(value = "/readfile",
            method = RequestMethod.GET,
            produces = "application/json")
    public Attachment readfile(@RequestParam String filename) {
        log.info("Received request to read page");
        return attachmentService.readAttachment(filename);
    }

    @RequestMapping(value = "/deletefile",
            method = RequestMethod.DELETE,
            produces = "application/json")
    public String deletefile(@RequestParam String filename) {
        log.info("Received request to delete page");
        return attachmentService.deleteAttachment(filename);
    }

}
