package com.proofpoint.wikisystem.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAttachmentArgs {
    private String filename;
    private String contents;
    private String ownerId;

    @Override
    public String toString() {
        return "CreateAttachmentArgs{" +
                "filename='" + filename + '\'' +
                ", contents='" + contents + '\'' +
                ", ownerId='" + ownerId + '\'' +
                '}';
    }
}
