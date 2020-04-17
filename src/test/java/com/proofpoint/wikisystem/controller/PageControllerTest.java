package com.proofpoint.wikisystem.controller;

import com.proofpoint.wikisystem.model.Attachment;
import com.proofpoint.wikisystem.model.Page;
import com.proofpoint.wikisystem.payload.CreatePageDto;
import com.proofpoint.wikisystem.service.PageService;
import com.proofpoint.wikisystem.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static com.proofpoint.wikisystem.util.Constants.STATUS_SUCCESS;
import static com.proofpoint.wikisystem.util.TestConstants.*;
import static com.proofpoint.wikisystem.util.TestConstants.REQUESTER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class PageControllerTest {

    @InjectMocks
    private PageController pageController;

    @Mock
    private PageService pageService;

    @Mock
    private UserService userService;

    private Page page;

    @BeforeEach
    void setup() throws Exception {
//        mvc = MockMvcBuilders.standaloneSetup(attachment).build();
        MockitoAnnotations.initMocks(this);
//        page = Page
    }

    @Test
    final void testRead() throws Exception {

        when(pageService.readPage(PAGE_ID, REQUESTER_ID,true)).thenReturn(PAGE);
        ResponseEntity<Page> output = pageController.read(PAGE_ID, REQUESTER_ID, "true");
        assertNotNull(output);
        assertEquals(200, output.getStatusCode().value());
        assertEquals("SamplePage", Objects.requireNonNull(output.getBody()).getPageID());
        assertEquals("Dummy content for page", output.getBody().getContent());
    }

    @Test
    final void testCreate() {
        CreatePageDto createPageDto = new CreatePageDto();
        createPageDto.setPageId(PAGE_ID);
        createPageDto.setParentPageId(PARENT_PAGE_ID);
        createPageDto.setContent(PAGE_CONTENT);
        createPageDto.setOwnerId(USER_ID);



        when(userService.read(USER_ID)).thenReturn(OWNER);

        ResponseEntity<String> response  = pageController.create(createPageDto);
        assertNotNull(response);
        assertEquals(201, response.getStatusCode().value());
        assertEquals(STATUS_SUCCESS, response.getBody());
    }

    @Test
    final void testCreatePage_FailedToReadUser() {
        CreatePageDto createPageDto = new CreatePageDto();
        createPageDto.setPageId(PAGE_ID);
        createPageDto.setParentPageId(PARENT_PAGE_ID);
        createPageDto.setContent(PAGE_CONTENT);
        createPageDto.setOwnerId(USER_ID);

        when(userService.read(USER_ID)).thenThrow(new RuntimeException("Dummy Exception"));
        ResponseEntity<String> response = pageController.create(createPageDto);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("Operation FAILED Message:Dummy Exception", response.getBody());
    }

    @Test
    final void testCreatePage_FailedToCreatePage() throws Exception {
        CreatePageDto createPageDto = new CreatePageDto();
        createPageDto.setPageId(PAGE_ID);
        createPageDto.setParentPageId(PARENT_PAGE_ID);
        createPageDto.setContent(PAGE_CONTENT);
        createPageDto.setOwnerId(USER_ID);

        when(userService.read(USER_ID)).thenReturn(OWNER);
        doThrow(new RuntimeException("Dummy Exception")).when(pageService).create(PAGE_ID, PARENT_PAGE_ID, OWNER, PAGE_CONTENT, createPageDto.getAccessMap());
        ResponseEntity<String> response = pageController.create(createPageDto);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("Operation FAILED Message:Dummy Exception", response.getBody());
    }

    @Test
    final void testUpdatePage(){
        when(pageService.update(PAGE_ID, UPDATEPAGEDTO, REQUESTER_ID)).thenReturn("Successfully updated page");
        ResponseEntity<String> output = pageController.update(PAGE_ID, UPDATEPAGEDTO);
        assertNotNull(output);
        assertEquals(200, output.getStatusCode().value());
    }
}
