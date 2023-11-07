package com.example.backend.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.db.entity.Meeting;
import org.mapstruct.Mapper;

@Mapper
public interface MeetingMapper extends BaseMapper<Meeting> {
}
