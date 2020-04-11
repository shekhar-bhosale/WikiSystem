package com.proofpoint.wikisystem.service;

import com.proofpoint.wikisystem.entities.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service @Slf4j
public class AttachmentService {
    private Map<String,Attachment> attachments = new HashMap<>();

    public void createAttachment(String filename, String contents){
        Attachment attachment = Attachment.builder()
                .filename(filename)
                .contents(contents)
                .build();
        log.info("Attachment created:"+attachment.toString());
        attachments.put(filename, attachment);
    }

    public Attachment readAttachment(String filename){
        if(attachments.containsKey(filename)){
            return attachments.get(filename);
        }else{
            return null;
        }
    }

    public String deleteAttachment(String filename){
        if(attachments.containsKey(filename)){
            attachments.remove(filename);
            return "File deleted successfully";
        }else{
            return "File not found";
        }
    }
}
