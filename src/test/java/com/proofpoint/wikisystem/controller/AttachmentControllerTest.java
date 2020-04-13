package com.proofpoint.wikisystem.controller;

import com.proofpoint.wikisystem.model.Attachment;
import com.proofpoint.wikisystem.service.AttachmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class AttachmentControllerTest {

    @InjectMocks
    AttachmentController attachmentController;

    @Mock
    AttachmentService attachmentService;

    private Attachment attachment;

    private final static String FILE_NAME = "Sample.txt";
    private final static String CONTENT = "Random data not important";

    @BeforeEach
    void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        attachment = Attachment.Builder
                .newInstance()
                .withFilename(FILE_NAME)
                .withContents(CONTENT)
                .build();
    }

    @Test
    final void testRead() {
        when(attachmentService.read(anyString())).thenReturn(attachment);
        ResponseEntity<Attachment> output = attachmentController.read(FILE_NAME);
        assertNotNull(output);
        assertEquals(200, output.getStatusCode().value());
        assertEquals("Sample.txt", Objects.requireNonNull(output.getBody()).getFilename());
        assertEquals("Random data not important", output.getBody().getContents());
    }

    @Test
    final void testCreate() {

    }

}
