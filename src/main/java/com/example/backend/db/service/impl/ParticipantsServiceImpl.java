package com.example.backend.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.bean.Result;
import com.example.backend.db.entity.Participants;
import com.example.backend.db.mapper.ParticipantsMapper;
import com.example.backend.db.service.ParticipantsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipantsServiceImpl extends ServiceImpl<ParticipantsMapper, Participants> implements ParticipantsService {
    @Override
    public Result<Object> addParticipants(List<Integer> participants, Integer meeting) {
        List<Participants> participantsList = new ArrayList<>();
        for (Integer participant:
             participants) {
            Participants item = new Participants();
            item.init(meeting, participant);
            participantsList.add(item);
        }
        if (saveBatch(participantsList)) {
            return Result.success(null);
        }
        return Result.fail("保存失败");
    }

    @Override
    public List<Participants> getByAccount(Integer account) {
       return list(new LambdaQueryWrapper<Participants>().eq(Participants::getAccount, account));
    }
}
