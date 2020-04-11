package com.proofpoint.wikisystem.service;

import com.proofpoint.wikisystem.entities.Attachment;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AttachmentService {
    private List<Attachment> attachments;

    public void createAttachment(String filename){
        Attachment attachment = Attachment.builder()
                .filename(filename)
                .build();

    }
}
