package com.example.backend.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;
import java.util.List;

public class Meeting {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer creator;
    private List<User> member;
    private boolean finished;
    private boolean canceled;
    private Date startTime;
    private Date endTime;

}
