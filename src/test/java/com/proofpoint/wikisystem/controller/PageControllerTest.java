package com.proofpoint.wikisystem.controller;

import com.proofpoint.wikisystem.model.Attachment;
import com.proofpoint.wikisystem.model.Page;
import com.proofpoint.wikisystem.service.PageService;
import com.proofpoint.wikisystem.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
}
