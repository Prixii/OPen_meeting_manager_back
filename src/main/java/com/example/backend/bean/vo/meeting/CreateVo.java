package com.example.backend.bean.vo.meeting;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CreateVo {
    private Integer creator;
    private String name;
    private List<Integer> participants;
    private String  startTime;
    private String  endTime;
}
