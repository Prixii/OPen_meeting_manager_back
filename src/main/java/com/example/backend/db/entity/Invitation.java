package com.example.backend.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Invitation {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer groupId;
    private Integer userId;
    private String state;
}
