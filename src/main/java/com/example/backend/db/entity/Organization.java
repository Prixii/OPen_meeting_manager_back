package com.example.backend.db.entity;

import com.example.backend.util.CommonUtil;
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
        dissolved = 0;
        id = (this.toString() + CommonUtil.randomize()).hashCode();
        return id;
    }
}
