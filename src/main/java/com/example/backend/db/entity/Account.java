package com.example.backend.db.entity;

import lombok.Data;

@Data
public class Account {
    private Integer id;
    private String name;
    private String phone;
    private String password;

    public Integer setId() {
        id = (name + phone + password).hashCode();
        return id;
    }
}
