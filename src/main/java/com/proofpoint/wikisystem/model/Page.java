package com.proofpoint.wikisystem.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Setter
@Getter
@Slf4j
public class Page extends Component {

    private String pageID;
    private String parentPageID;
    private List<Attachment> attachments;
    private String content;

    private Page(PageBuilder pageBuilder) {
        this.owner = pageBuilder.owner;
        this.pageID = pageBuilder.pageID;
        this.parentPageID = pageBuilder.parentPageID;
        this.accessMap = new HashMap<AccessType, List<Collaborator>>();
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageID='" + pageID + '\'' +
                ", parentPageID='" + parentPageID + '\'' +
                ", attachments=" + attachments +
                ", owner=" + owner +
                ", accessMap=" + accessMap +
                '}';
    }

    public User getOwner() {
        return this.owner;
    }

    public void create() {
        System.out.println("Creating Page");
        log.info("Logging");
    }

    public void addAttachment(Attachment attachment) {
        if (this.attachments == null) {
            this.attachments = new ArrayList<Attachment>();
        }
        attachments.add(attachment);
    }

    public void delete() {
        System.out.println("Deleting Page");
    }

    public void update() {
        System.out.println("Updating Page");
    }

    public static class PageBuilder {
        private User owner;
        private String pageID;
        private String parentPageID;

        private PageBuilder() {
        }

        public static PageBuilder newInstance() {
            return new PageBuilder();
        }

        public PageBuilder withPageID(String pageID) {
            this.pageID = pageID;
            return this;
        }

        public PageBuilder withParentPageID(String parentPageID) {
            this.parentPageID = parentPageID;
            return this;
        }

        public PageBuilder withOwner(User owner) {
            this.owner = owner;
            return this;
        }

        public Page build() {
            return new Page(this);
        }

    }
}
