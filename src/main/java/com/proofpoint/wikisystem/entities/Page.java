package com.proofpoint.wikisystem.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
@Setter
@Builder
public class Page extends Component {

    private String pageID;
    private String parentPageID;
    private List<Attachment> attachments;

    public void create(){
        System.out.println("Creating Page");
//        log.info("Logging");
    }

    public void delete(){
        System.out.println("Deleting Page");
    }

    public void update(){
        System.out.println("Updating Page");
    }
}
