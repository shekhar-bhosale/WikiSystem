package com.proofpoint.wikisystem.service;

import com.proofpoint.wikisystem.model.*;
import com.proofpoint.wikisystem.payload.UpdateComponentDto;
import com.proofpoint.wikisystem.util.Action;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.proofpoint.wikisystem.util.Constants.authorizedActionsMap;

@Service
@Slf4j
@Scope("singleton")
public class AttachmentService {
    private Map<String, Attachment> attachments = new HashMap<>();

    @Autowired
    private AccessService accessService;

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    public void create(String filename, String contents, User owner, Map<String, String> accessMap) throws Exception {
        Attachment attachment = Attachment
                .Builder
                .newInstance()
                .withFilename(filename)
                .withContents(contents)
                .withOwner(owner)
                .build();

        if (accessMap != null) {
            log.info("Assigning access rights to component");
            for (String collaboratorId : accessMap.keySet()) {
                Collaborator collaborator;
                collaborator = userService.read(collaboratorId);
                if (collaborator == null) {
                    collaborator = teamService.read(collaboratorId);
                    if (collaborator == null) {
                        log.error("User does not exist");
                        throw new Exception("Given user in access map does not exist");
                    }
                }
                accessService.assignAccess(attachment, AccessType.valueOf(accessMap.get(collaboratorId)), collaborator);
            }
        }
        log.info("Attachment created:" + attachment.toString());
        attachments.put(filename, attachment);
    }

    public Attachment read(String filename) {
        if (attachments.containsKey(filename)) {
            Attachment output = attachments.get(filename);
//            log.info("Attachment found:" + output.toString());
            return output;
        } else {
            return null;
        }
    }

    public Attachment readAttachment(String filename, String requesterId, Boolean isIndividualUser) {
        if (isAuthorizedtoPerformAction(Action.READ, filename, requesterId, isIndividualUser)) {
            log.info("User " + requesterId + " is authorized to read this component.");
            Attachment output = read(filename);
            log.info("Attachment found:" + output.toString());
            return output;
        } else {
            return null;
        }
    }

    public String update(String filename, UpdateComponentDto updateArgs, String requesterId) {
        if (isAuthorizedtoPerformAction(Action.UPDATE, filename, requesterId, Boolean.parseBoolean(updateArgs.getIsIndividualUser()))) {
            if (attachments.containsKey(filename)) {
                Attachment attachment = attachments.get(filename);
                if (updateArgs.getContents() != null) {
                    attachment.setContents(updateArgs.getContents());
                }

                if (updateArgs.getOwnerId() != null) {
                    if (isRequesterisOwner(attachment, requesterId)) {
                        log.info("Transferring ownership of file");
                        User owner = userService.read(updateArgs.getOwnerId());
                        attachment.setOwner(owner);
                    }
                }
                attachments.put(filename, attachment);
                return "Successfully updated attachment";
            } else {
                return "Attachment not found";
            }
        } else {
            return "Not authorized to perform action on given component";
        }
    }

    private boolean isRequesterisOwner(Attachment attachment, String requesterId) {
        return attachment.getOwner().getUsername().equals(requesterId);
    }

    public boolean delete(String filename, String requesterId, Boolean isIndividualUser) {
        if (isAuthorizedtoPerformAction(Action.DELETE, filename, requesterId, isIndividualUser)) {
            if (attachments.containsKey(filename)) {
                attachments.remove(filename);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean isAuthorizedtoPerformAction(Action action, String filename, String requesterId, boolean isIndividualUser) {

        Attachment attachment = read(filename);

        if (isRequesterisOwner(attachment, requesterId)) {
            return true;
        }

        Collaborator collaborator;
        if (isIndividualUser) {
            collaborator = userService.read(requesterId);
        } else {
            Team team = teamService.read(requesterId);
            if (team.isAdmin()) {
                return true;
            }
            collaborator = team;
        }

        List<AccessType> allowedAccessTypes = authorizedActionsMap.get(action);

        Map<AccessType, List<Collaborator>> accessMap = attachment.getAccessMap();

        for (AccessType allowedAccessType : allowedAccessTypes) {
            log.info(("Checking if User " + collaborator.getId() + " has access " + allowedAccessType));
            List<Collaborator> collabList = accessMap.get(allowedAccessType);
            if (collabList != null) {
                if (collabList.contains(collaborator)) {
                    return true;
                }
            }
        }

        return false;
    }

}
