package com.example.backend.db.entity;

import lombok.Data;

@Data
public class Invitation {
    private Integer id;
    private Integer organization;
    private Integer account;
    private String state;
}
