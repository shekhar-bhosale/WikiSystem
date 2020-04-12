package com.proofpoint.wikisystem.service;

import com.proofpoint.wikisystem.model.Attachment;
import com.proofpoint.wikisystem.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service @Slf4j
@Scope("singleton")
public class AttachmentService {
    private Map<String,Attachment> attachments = new HashMap<>();

    public void create(String filename, String contents, User owner){
        Attachment attachment = Attachment
                .Builder
                .newInstance()
                .withFilename(filename)
                .withContents(contents)
                .withOwner(owner)
                .build();
        log.info("Attachment created:"+attachment.toString());
        attachments.put(filename, attachment);
    }

    public Attachment read(String filename){
        if(attachments.containsKey(filename)){
            Attachment output = attachments.get(filename);
            log.info("Attachment found:"+output.toString());
            return output;
        }else{
            return null;
        }
    }

    public boolean delete(String filename){
        if(attachments.containsKey(filename)){
            attachments.remove(filename);
            return true;
        }else{
            return false;
        }
    }
}
