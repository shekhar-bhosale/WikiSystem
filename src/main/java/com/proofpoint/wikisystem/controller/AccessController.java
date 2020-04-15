package com.proofpoint.wikisystem.controller;

import com.proofpoint.wikisystem.exceptions.AccessDeniedException;
import com.proofpoint.wikisystem.model.AccessType;
import com.proofpoint.wikisystem.model.Collaborator;
import com.proofpoint.wikisystem.model.Component;
import com.proofpoint.wikisystem.payload.CreateAccessArgs;
import com.proofpoint.wikisystem.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.proofpoint.wikisystem.util.Constants.STATUS_FAILED_WITH_MESSAGE;
import static com.proofpoint.wikisystem.util.Constants.STATUS_SUCCESS;

@Slf4j
@RestController
@RequestMapping("/wikisystem/access")
public class AccessController {

    @Autowired
    private AccessService accessService;

    @Autowired
    private PageService pageService;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> create(@RequestBody final CreateAccessArgs payload) {

        try {
            log.info("Received request to create access");
            log.info("Payload received:" + payload.toString());

            Component component;
            Collaborator collaborator;

            if (payload.isPage()) {
                component = pageService.read(payload.getComponentId());
            } else {
                component = attachmentService.read(payload.getComponentId());
            }

            if (payload.isIndividualUser()) {
                collaborator = userService.read(payload.getCollaboratorId());
            } else {
                collaborator = teamService.read(payload.getCollaboratorId());
            }

            if (component == null || collaborator == null) {
                throw new AccessDeniedException("Given Entities does not exist in system.");
            }

            accessService.assignAccess(component, AccessType.valueOf(payload.getAccessType()), collaborator);
            return new ResponseEntity<>(STATUS_SUCCESS, HttpStatus.CREATED);

        } catch (final Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(STATUS_FAILED_WITH_MESSAGE + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

}
