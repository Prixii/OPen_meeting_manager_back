package com.example.backend.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.db.entity.Account;
import com.example.backend.db.entity.Member;
import com.example.backend.db.entity.Organization;
import com.example.backend.db.mapper.MemberMapper;
import com.example.backend.db.service.AccountService;
import com.example.backend.db.service.MemberService;
import com.example.backend.db.service.OrganizationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {
    @Resource
    OrganizationService organizationService;

    @Resource
    AccountService accountService;

    @Override
    public List<Organization> getOrganization(Integer memberId) {
        List<Member> members = list(new LambdaQueryWrapper<Member>().eq(Member::getAccount, memberId));
        List<Organization> organizations = new ArrayList<>();
        for (Member member:
                members ) {
           var item = organizationService.getById(member.getOrganization());
           if(item != null) {
              organizations.add(item);
            }
        }
        return organizations;
    }

    @Override
    public List<Account> getMember(Integer organizationId) {
        List<Member> members = list(new LambdaQueryWrapper<Member>().eq(Member::getOrganization, organizationId));
        List<Account> accounts = new ArrayList<>();
        for (Member member:
             members) {
            var item = accountService.getById(member.getAccount());
            if (item != null) {
                accounts.add(item);
            }
        }
        return accounts;
    }
}
