package com.example.backend.db.entity;

import lombok.Data;

import java.util.List;

@Data
public class Organization {
    private Integer id;
    private Integer creator;
    private String name;
    private List<Account> member;
    private boolean dissolved;

    public Integer generateId() {
        id = (creator + name).hashCode();
        return id;
    }
}
