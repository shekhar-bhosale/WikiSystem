package com.proofpoint.wikisystem.model;

public interface Operations {
    /*
    Pass through methods to add persistence/database layer in future
    */
    void create();

    void read();

    void delete();

    void update();
}
