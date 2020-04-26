package com.example.traveli.Model;

import java.io.Serializable;

public class Note implements Serializable  {

    private String name;

    private String description;

    public Note(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
