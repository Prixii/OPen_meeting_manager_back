package com.example.backend.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class Group {
    @TableId(type =  IdType.AUTO)
    private Integer id;

    private Integer creator;

    private List<User> member;

}
