package com.example.backend.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.db.entity.Account;
import com.example.backend.db.entity.Member;
import com.example.backend.db.entity.Organization;

import java.util.List;

public interface MemberService extends IService<Member> {
    List<Organization> getOrganization(Integer memberId);
    List<Account> getMember(Integer organizationId);
}
