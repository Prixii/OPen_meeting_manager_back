package com.example.backend.db.entity;

import com.example.backend.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private Integer id;
    private Integer account;
    private Integer organization;

    public void init(Object src) {
        if (src != null) {
            BeanUtils.copyProperties(src, this);
            System.out.println(CommonUtil.randomize());
            id = (this.toString() + CommonUtil.randomize()).hashCode();
        }
    }
}
