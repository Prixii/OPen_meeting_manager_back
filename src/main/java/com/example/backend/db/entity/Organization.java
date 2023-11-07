package com.example.backend.db.entity;

import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class Organization {
    private Integer id;
    private Integer creator;
    private String name;
    private Integer dissolved;

    public Integer init(Object src) {
        if (src != null) {
            BeanUtils.copyProperties(src, this);
        }
        id = (this.toString()).hashCode();
        return id;
    }
}
