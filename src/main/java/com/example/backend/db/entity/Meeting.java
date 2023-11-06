package com.example.backend.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Meeting{
    @TableId()
    private Integer id;
    private Integer creator;
    private Integer finished;
    private Integer canceled;
    private Date startTime;
    private Date endTime;

}
