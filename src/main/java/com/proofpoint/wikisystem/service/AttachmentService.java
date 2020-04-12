package com.proofpoint.wikisystem.service;

import com.proofpoint.wikisystem.model.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service @Slf4j
public class AttachmentService {
    private Map<String,Attachment> attachments = new HashMap<>();

    public void create(String filename, String contents){
        Attachment attachment = Attachment.builder()
                .filename(filename)
                .contents(contents)
                .build();
        log.info("Attachment created:"+attachment.toString());
        attachments.put(filename, attachment);
    }

    public Attachment read(String filename){
        if(attachments.containsKey(filename)){
            return attachments.get(filename);
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
