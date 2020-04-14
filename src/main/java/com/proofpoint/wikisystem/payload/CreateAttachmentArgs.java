package com.proofpoint.wikisystem.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CreateAttachmentArgs {
    private String filename;
    private String contents;
    private String ownerId;
    private Map<String, String> accessMap;

    @Override
    public String toString() {
        return "CreateAttachmentArgs{" +
                "filename='" + filename + '\'' +
                ", contents='" + contents + '\'' +
                ", ownerId='" + ownerId + '\'' +
                '}';
    }
}
