package com.example.backend.db.entity;

import com.example.backend.util.CommonUtil;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Data
public class Meeting{
    private Integer id;
    private String title;
    private Integer creator;
    private Integer finished;
    private Integer canceled;
    private String startTime;
    private String endTime;

    public Integer init(Object src) {
        if (src != null){
            BeanUtils.copyProperties(src, this);
        }
        canceled = 0;
        finished = 0;
        id = (this + CommonUtil.randomize()).hashCode();
        return id;
    }

    public boolean belongTo(Integer account) { return Objects.equals(creator, account); }

    public boolean available() {
        return (canceled == 0) && (finished == 0);
    }

    public void finish() { finished = 1; }
    public void cancel() { canceled = 1; }
}
