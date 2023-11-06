package com.example.backend.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class Group {
    @TableId()
    private Integer id;

    private Integer creator;

    private List<Account> member;

}
