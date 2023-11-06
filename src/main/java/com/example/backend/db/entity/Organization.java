package com.example.backend.db.entity;

import lombok.Data;

import java.util.List;

@Data
public class Organization {
    private Integer id;
    private Integer creator;
    private String name;
    private Integer dissolved;

    public Integer generateId() {
        id = (creator + name).hashCode();
        return id;
    }
}
