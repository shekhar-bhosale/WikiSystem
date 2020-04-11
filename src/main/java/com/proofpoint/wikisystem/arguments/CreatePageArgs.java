package com.proofpoint.wikisystem.arguments;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreatePageArgs {
    String pageId;
    String parentPageId;
}
