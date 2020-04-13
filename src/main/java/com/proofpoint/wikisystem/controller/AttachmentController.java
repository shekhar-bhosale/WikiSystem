package com.proofpoint.wikisystem.controller;

import com.proofpoint.wikisystem.model.Attachment;
import com.proofpoint.wikisystem.model.User;
import com.proofpoint.wikisystem.payload.CreateAttachmentArgs;
import com.proofpoint.wikisystem.service.AttachmentService;
import com.proofpoint.wikisystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.proofpoint.wikisystem.util.Constants.STATUS_FAILED;
import static com.proofpoint.wikisystem.util.Constants.STATUS_SUCCESS;

@Slf4j
@RestController
@RequestMapping("/wikisystem/attachment")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> create(@RequestBody final CreateAttachmentArgs payload) {

        try {
            log.info("Received request to create attachment");
            log.info("Payload:" + payload.toString());

            User owner = userService.read(payload.getOwnerId());
            attachmentService.create(payload.getFilename(), payload.getContents(), owner);

            return new ResponseEntity<>(STATUS_SUCCESS, HttpStatus.CREATED);

        } catch (final Exception e) {
            log.error("Caught Exception while creating attachment. " + e.getMessage());
            return new ResponseEntity<>(STATUS_FAILED + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Attachment> read(@RequestParam final String fileName) {
        log.info("Received request to read attachment");

        final Attachment output = attachmentService.read(fileName);
        ResponseEntity<Attachment> response;
        if (output != null) {
            response = new ResponseEntity<>(output, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(output, HttpStatus.NOT_FOUND);
        }

        //TODO: Construct Response
        return response;
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<String> delete(@RequestParam final String fileName) {

        log.info("Received request to delete attachment");

        ResponseEntity<String> response;
        if (attachmentService.delete(fileName)) {
            response = new ResponseEntity<>("File deleted successfully", HttpStatus.OK);
        } else {
            response = new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        //TODO: Construct Response
        return response;

    }
}
