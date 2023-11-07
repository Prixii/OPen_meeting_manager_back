package com.example.backend.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.bean.Result;
import com.example.backend.db.entity.Participants;

import java.util.List;

public interface ParticipantsService extends IService<Participants> {
    Result<Object> addParticipants(List<Integer> participants, Integer meeting);
    List<Participants> getByAccount(Integer account);
}
