package com.proofpoint.wikisystem.service;

import com.proofpoint.wikisystem.model.AccessType;
import com.proofpoint.wikisystem.model.Attachment;
import com.proofpoint.wikisystem.model.Collaborator;
import com.proofpoint.wikisystem.model.User;
import com.proofpoint.wikisystem.payload.UpdateComponentArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

        if(accessMap!=null){
            log.info("Assigning access rights to component");
            for(String collaboratorId: accessMap.keySet()){
                Collaborator collaborator;
                collaborator = userService.read(collaboratorId);
                if(collaborator==null){
                    collaborator = teamService.read(collaboratorId);
                    if(collaborator==null) {
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
            log.info("Attachment found:" + output.toString());
            return output;
        } else {
            return null;
        }
    }

    public String update(String filename, UpdateComponentArgs updateArgs, String requesterId){
        if(attachments.containsKey(filename)){
            Attachment attachment = attachments.get(filename);
            if(updateArgs.getContents()!=null){
                attachment.setContents(updateArgs.getContents());
            }

            if(updateArgs.getOwnerId() != null){
                if(isAuthorized(attachment, requesterId)){
                    log.info("Transferring ownership of file");
                    User owner = userService.read(updateArgs.getOwnerId());
                    attachment.setOwner(owner);
                }
            }
            return "Successfully updated attachment";
        }else{
            return "Attachment not found";
        }
    }

    private boolean isAuthorized(Attachment attachment, String requesterId){
        return attachment.getOwner().getUsername().equals(requesterId);
    }

    public boolean delete(String filename) {
        if (attachments.containsKey(filename)) {
            attachments.remove(filename);
            return true;
        } else {
            return false;
        }
    }
}
