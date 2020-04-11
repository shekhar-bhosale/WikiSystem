package com.proofpoint.wikisystem.payload;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreatePageArgs {
    private String pageId;
    private String parentPageId;

    @Override
    public String toString() {
        return "CreatePageArgs{" +
                "pageId='" + pageId + '\'' +
                ", parentPageId='" + parentPageId + '\'' +
                '}';
    }
}
