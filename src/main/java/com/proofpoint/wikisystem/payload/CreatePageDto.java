package com.proofpoint.wikisystem.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CreatePageDto {
    private String pageId;
    private String parentPageId;
    private String ownerId;
    private String content;
    private Map<String, String> accessMap;

    @Override
    public String toString() {
        return "CreatePageArgs{" +
                "pageId='" + pageId + '\'' +
                ", parentPageId='" + parentPageId + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", content='" + content + '\'' +
                ", accessMap=" + accessMap +
                '}';
    }
}

