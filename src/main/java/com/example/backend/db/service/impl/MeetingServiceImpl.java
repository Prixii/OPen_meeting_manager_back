package com.example.backend.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.bean.Result;
import com.example.backend.db.entity.Meeting;
import com.example.backend.db.entity.Participants;
import com.example.backend.db.mapper.MeetingMapper;
import com.example.backend.db.service.MeetingService;
import com.example.backend.db.service.ParticipantsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MeetingServiceImpl extends ServiceImpl<MeetingMapper, Meeting> implements MeetingService {
    @Resource
    ParticipantsService participantsService;

    @Override
    public List<Meeting> getByParticipants(List<Participants> participantsList) {
        List<Meeting> list = new ArrayList<>();
        for (Participants participant:
             participantsList) {
            Meeting item = getById(participant.getMeeting());
            if (item != null) {
                list.add(item);
            }
        }
        return list;
    }

    @Override
    public List<Meeting> getByAccount(Integer account) {
        List<Participants> participantsList = participantsService.getByAccount(account);
        return getByParticipants(participantsList);
    }

    @Override
    public List<Meeting> getByCreator(Integer creator) {
        return list(new LambdaQueryWrapper<Meeting>().eq(Meeting::getCreator, creator));
    }
}
