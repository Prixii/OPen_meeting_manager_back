package com.example.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.bean.Result;
import com.example.backend.bean.vo.meeting.CancelVo;
import com.example.backend.bean.vo.meeting.CreateVo;
import com.example.backend.db.entity.Meeting;
import com.example.backend.db.entity.Participants;
import com.example.backend.db.service.MeetingService;
import com.example.backend.db.service.ParticipantsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("meeting")
@Api( tags = "Meeting")
public class MeetingController {
    @Resource
    MeetingService meetingService;

    @Resource
    ParticipantsService participantsService;

    @ApiOperation("创建会议")
    @PostMapping("/create")
    Result<Integer> create(@RequestBody CreateVo vo) {
        Meeting newMeeting = new Meeting();
        Integer meetingId = newMeeting.init(vo);
        if(meetingService.save(newMeeting)) {
            Result<Object> saveResult = participantsService.addParticipants(vo.getParticipants(), meetingId);
            if (saveResult.getCode() == 200) {
                return Result.success(meetingId);
            }
        }
        return Result.fail("保存错误");
    }

    @ApiOperation("取消会议")
    @PostMapping("/cancel")
    Result<Object> cancel(@RequestBody CancelVo vo) {
        Meeting target = meetingService.getOne(new LambdaQueryWrapper<Meeting>().eq(Meeting::getId, vo.getMeeting()));
        if (target == null) {
            return Result.fail("对象不存在");
        }
        if (!target.belongTo(vo.getCreator())) {
            return Result.fail(401, "权限不足", null);
        }
        if (!target.available()) {
            return Result.success(200);
        }

        target.cancel();
        if (meetingService.updateById(target)) {
            return Result.success("取消成功");
        }
        return Result.fail("未知错误");
    }

    @ApiOperation("完成会议")
    @PostMapping("/finish")
    Result<Object> finish(@RequestBody CancelVo vo) {
        Meeting target = meetingService.getOne(new LambdaQueryWrapper<Meeting>().eq(Meeting::getId, vo.getMeeting()));
        if (target == null) {
            return Result.fail("对象不存在");
        }
        if (!target.belongTo(vo.getCreator())) {
            return Result.fail(401, "权限不足", null);
        }
        if (!target.available()) {
            return Result.success(200);
        }

        target.finish();
        if (meetingService.updateById(target)) {
            return Result.success("会议完成");
        }
        return Result.fail("未知错误");
    }

    @ApiOperation("创建的会议")
    @GetMapping("/manage")
    Result<List<Meeting>> manage(@RequestParam Integer creator) {
        return Result.success(meetingService.getByCreator(creator));
    }


    @ApiOperation("会议列表")
    @GetMapping("/list")
    Result<List<Meeting>> list(@RequestParam Integer account) {
        return Result.success( meetingService.getByAccount(account));
    }

}
