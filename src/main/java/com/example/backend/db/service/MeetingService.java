package com.example.backend.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.db.entity.Meeting;
import com.example.backend.db.entity.Participants;

import java.util.List;

public interface MeetingService extends IService<Meeting> {
    List<Meeting> getByParticipants(List<Participants> participantsList);
    List<Meeting> getByAccount(Integer account);
    List<Meeting> getByCreator(Integer account);

}
