package com.example.backend.db.entity;

import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class Account {
    private Integer id;
    private String name;
    private String phone;
    private String password;

    public Integer init(Object src) {
        if (src != null){
            BeanUtils.copyProperties(src, this);
        }
        id = (name + phone + password).hashCode();
        return id;
    }
}
