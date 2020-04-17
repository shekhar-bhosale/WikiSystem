package com.proofpoint.wikisystem.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Collaborator implements Operations {
    protected String Id;
}
