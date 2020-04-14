package com.proofpoint.wikisystem.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAttachmentArgs {
    private String contents;
    private String ownerId;
}
