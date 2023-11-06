package com.example.backend.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.db.entity.Invitation;
import com.example.backend.db.mapper.InvitationMapper;
import com.example.backend.db.service.InvitationService;
import org.springframework.stereotype.Service;

@Service
public class InvitationServiceImpl extends ServiceImpl<InvitationMapper, Invitation> implements InvitationService {
}
