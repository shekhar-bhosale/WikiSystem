package com.proofpoint.wikisystem.controller;

import com.proofpoint.wikisystem.model.Attachment;
import com.proofpoint.wikisystem.payload.CreateAttachmentDto;
import com.proofpoint.wikisystem.service.AttachmentService;
import com.proofpoint.wikisystem.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.doThrow;
import static com.proofpoint.wikisystem.util.TestConstants.*;


import static org.junit.jupiter.api.Assertions.*;
import static com.proofpoint.wikisystem.util.Constants.STATUS_SUCCESS;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class AttachmentControllerTest {

    @InjectMocks
    AttachmentController attachmentController;

    @Mock
    AttachmentService attachmentService;

    @Mock
    UserService userService;

//    private MockMvc mvc;

    /*private Attachment attachment;

    private final static String FILE_NAME = "Sample.txt";
    private final static String CONTENT = "Random data not important";
    private final static String USERID = "User101";*/

    @BeforeEach
    void setup() throws Exception {
//        mvc = MockMvcBuilders.standaloneSetup(attachment).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    final void testRead() throws Exception {

       /* mvc.perform(get("/attachment")
                .param("fileName", "Sample.txt"))
                .andExpect(status().isOk());*/

        when(attachmentService.read(anyString())).thenReturn(ATTACHMENT);
        ResponseEntity<Attachment> output = attachmentController.read(FILE_NAME);
        assertNotNull(output);
        assertEquals(200, output.getStatusCode().value());
        assertEquals("Sample.txt", Objects.requireNonNull(output.getBody()).getFilename());
        assertEquals("Random data not important", output.getBody().getContents());
    }

    @Test
    final void testCreate() {
        CreateAttachmentDto createAttachmentDto = new CreateAttachmentDto();
        createAttachmentDto.setFilename(FILE_NAME);
        createAttachmentDto.setContents(FILE_CONTENT);
        createAttachmentDto.setOwnerId(USER_ID);



        when(userService.read(USER_ID)).thenReturn(OWNER);

        ResponseEntity<String> response  = attachmentController.create(createAttachmentDto);
        assertNotNull(response);
        assertEquals(201, response.getStatusCode().value());
        assertEquals(STATUS_SUCCESS, response.getBody());
    }

    @Test
    final void testCreateAttachment_FailedToReadUser() {
        CreateAttachmentDto createAttachmentDto = new CreateAttachmentDto();
        createAttachmentDto.setFilename(FILE_NAME);
        createAttachmentDto.setContents(FILE_CONTENT);
        createAttachmentDto.setOwnerId(USER_ID);

        when(userService.read(USER_ID)).thenThrow(new RuntimeException("Dummy Exception"));
        ResponseEntity<String> response = attachmentController.create(createAttachmentDto);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("Operation FAILED Message:Dummy Exception", response.getBody());
    }

    @Test
    final void testCreateAttachment_FailedToCreateAttachment() throws Exception {
        CreateAttachmentDto createAttachmentDto = new CreateAttachmentDto();
        createAttachmentDto.setFilename(FILE_NAME);
        createAttachmentDto.setContents(FILE_CONTENT);
        createAttachmentDto.setOwnerId(USER_ID);

        when(userService.read(USER_ID)).thenReturn(OWNER);
        doThrow(new RuntimeException("Dummy Exception")).when(attachmentService).create(FILE_NAME, FILE_CONTENT, OWNER, createAttachmentDto.getAccessMap());
        ResponseEntity<String> response = attachmentController.create(createAttachmentDto);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("Operation FAILED Message:Dummy Exception", response.getBody());
    }

}
