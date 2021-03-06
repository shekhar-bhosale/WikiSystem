package com.proofpoint.wikisystem.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class Attachment extends Component {

    private String filename;
    private String contents;

    private Attachment(Builder builder) {
        this.filename = builder.filename;
        this.contents = builder.contents;
        this.owner = builder.owner;
        this.accessMap = new HashMap<AccessType, List<Collaborator>>();
    }

    public User getOwner() {
        return this.owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "filename='" + filename + '\'' +
                ", contents='" + contents + '\'' +
                ", owner=" + owner +
                ", accessMap=" + accessMap +
                '}';
    }

    public void create() {
        System.out.println("Creating Attachment");
    }

    public void read() {
        System.out.println("Reading Page");
    }

    public void delete() {
        System.out.println("Deleting Attachment");
    }

    public void update() {
        System.out.println("Updating Attachment");
    }

    public static class Builder {
        private User owner;
        private String filename;
        private String contents;

        private Builder() {
        }

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder withFilename(String filename) {
            this.filename = filename;
            return this;
        }

        public Builder withContents(String contents) {
            this.contents = contents;
            return this;
        }

        public Builder withOwner(User owner) {
            this.owner = owner;
            return this;
        }

        public Attachment build() {
            return new Attachment(this);
        }

    }
}
