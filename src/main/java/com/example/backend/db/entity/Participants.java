package com.example.backend.db.entity;

import com.example.backend.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Participants {
    private Integer id;
    private Integer meeting;
    private Integer account;

    public Integer init(Integer meeting, Integer account) {
        this.meeting = meeting;
        this.account = account;
        id = (this.toString() + CommonUtil.currentTime()).hashCode();
        return id;
    }
}
