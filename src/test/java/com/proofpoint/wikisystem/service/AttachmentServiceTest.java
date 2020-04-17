package com.proofpoint.wikisystem.service;

import com.proofpoint.wikisystem.model.Attachment;
import com.proofpoint.wikisystem.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class AttachmentServiceTest {

    @InjectMocks
    AttachmentService attachmentService;

    @Mock
    Map<String, Attachment> attachments;

    @Mock
    User user;

    @BeforeEach
    void setup() throws Exception{
        MockitoAnnotations.initMocks(this);
        attachments = new HashMap<>();
    }

    @Test
    final void testCreate(){
        fail("Not yet implemented");
    }

    @Test
    final void testRead(){
       Attachment attachment = Attachment.Builder
                                .newInstance()
                                .withFilename("Sample.txt")
                                .withContents("Random content")
                                .withOwner(user)
                                .build();

       when(attachments.get(anyString())).thenReturn(attachment);

        Attachment attachmentv2 = attachmentService.read(anyString());
        assertNotNull(attachmentv2);
        assertEquals("Sample.txt",attachmentv2.getFilename());

    }
}
