package com.proofpoint.wikisystem.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
//@Builder
public class Page extends Component {

    private String pageID;
    private String parentPageID;
    private List<Attachment> attachments;

    public Page(PageBuilder pageBuilder){
        this.owner = pageBuilder.owner;
        this.pageID = pageBuilder.pageID;
        this.parentPageID = pageBuilder.parentPageID;
    }

    public static class PageBuilder{
        private User owner;
        private String pageID;
        private String parentPageID;

        public static PageBuilder newInstance(){
            return new PageBuilder();
        }

        private PageBuilder() {}

        public PageBuilder withPageID(String pageID){
            this.pageID = pageID;
            return this;
        }

        public PageBuilder withParentPageID(String parentPageID){
            this.parentPageID = parentPageID;
            return this;
        }

        public Page build(){
            return new Page(this);
        }

    }

    public User getOwner(){
        return this.owner;
    }

    public void setOwner(User owner){
        this.owner = owner;
    }

    public void create(){
        System.out.println("Creating Page");
        log.info("Logging");
    }

    public void addAttachment(Attachment attachment){
        if(this.attachments==null){
            this.attachments = new ArrayList<Attachment>();
        }
        attachments.add(attachment);
    }

    public void delete(){
        System.out.println("Deleting Page");
    }

    public void update(){
        System.out.println("Updating Page");
    }
}
