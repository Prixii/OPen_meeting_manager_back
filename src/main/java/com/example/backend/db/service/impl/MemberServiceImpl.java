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
    public List<Organization> getOrganization(Integer member) {
        List<Member> members = list(new LambdaQueryWrapper<Member>().eq(Member::getAccount, member));
        List<Organization> organizations = new ArrayList<>();
        for (Member memberItem:
                members ) {
           var item = organizationService.getById(memberItem.getOrganization());
           if(item != null) {
              organizations.add(item);
            }
        }
        return organizations;
    }

    @Override
    public List<Account> getMember(Integer organization) {
        List<Member> members = list(new LambdaQueryWrapper<Member>().eq(Member::getOrganization, organization));
        List<Account> accounts = new ArrayList<>();
        for (Member memberItem:
             members) {
            var item = accountService.getById(memberItem.getAccount());
            if (item != null) {
                accounts.add(item);
            }
        }
        return accounts;
    }

    @Override
    public void addMember(Integer account, Integer organization) {
        Member newMember = new Member((account.toString() + organization.toString()).hashCode(),account, organization);
        save(newMember);
    }
}
