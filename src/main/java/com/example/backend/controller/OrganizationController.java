package com.example.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.backend.bean.Result;
import com.example.backend.bean.dto.organization.ListDto;
import com.example.backend.bean.dto.organization.MemberDto;
import com.example.backend.bean.vo.organization.*;
import com.example.backend.db.entity.Account;
import com.example.backend.db.entity.Member;
import com.example.backend.db.entity.Organization;
import com.example.backend.db.service.AccountService;
import com.example.backend.db.service.MemberService;
import com.example.backend.db.service.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("organization")
@Api(tags ="Organization")
public class OrganizationController {
    @Resource
    OrganizationService organizationService;

    @Resource
    AccountService accountService;

    @Resource
    MemberService memberService;

    @ApiOperation("创建")
    @PostMapping("/create")
    public Result<Integer> create(@RequestBody CreateVo vo) {
        var organizationId = organizationService.create(vo);
        if (organizationId != -1) {
            return Result.success(organizationId);
        } else {
            return Result.fail("未知错误，保存异常");
        }

    }

    @ApiOperation("解散")
    @PostMapping("/dissolve")
    public Result<Object> dissolve(@RequestBody DissolveVo vo) {
        Organization target =
        organizationService.getOne( new LambdaQueryWrapper<Organization>().eq(Organization::getId, vo.getGroup()));
        if (target == null){
            return Result.fail("组织不存在");
        }

        System.out.println(target);
        if (!Objects.equals(target.getCreator(), vo.getCreator())) {
            return Result.fail(401, "权限不足", null);
        }
        target.setDissolved(1);
        organizationService.updateById(target);
        return Result.success("成功");
    }

    @ApiOperation("踢出")
    @PostMapping("/kick")
    public Result<Object> kick(@RequestBody KickVo vo) {
        Organization target =
                organizationService.getOne( new LambdaQueryWrapper<Organization>().eq(Organization::getId, vo.getOrganization()));
        if (target == null){
            return Result.fail("组织不存在");
        }
        if (!Objects.equals(target.getCreator(), vo.getCreator())) {
            return Result.fail(401, "权限不足", null);
        }
        Account account = accountService.getOne(new LambdaQueryWrapper<Account>().eq(Account::getId, vo.getAccount()));
        if (account == null){
            return Result.fail(401, "用户不在组织内或不存在", null);
        }
        memberService.removeMember(vo.getAccount(), vo.getOrganization());
        return Result.success("成功");
    }

    @ApiOperation("退出")
    @PostMapping("/leave")
    public Result<Object> leave(@RequestBody LeaveVo vo) {
        Organization target =
                organizationService.getOne( new LambdaQueryWrapper<Organization>().eq(Organization::getId, vo.getOrganization()));
        if (target == null){
            return Result.fail("组织不存在");
        }
        memberService.removeMember(vo.getAccount(), vo.getOrganization());
        return Result.success("成功");
    }

    @ApiOperation("成员")
    @GetMapping("/member")
    public Result<List<MemberDto>> member(@RequestParam Integer creator, @RequestParam Integer organization) {
        Organization target =
                organizationService.getOne( new LambdaQueryWrapper<Organization>().eq(Organization::getId, organization));
        if (target == null){
            return Result.fail("组织不存在");
        }
        if (!Objects.equals(target.getCreator(), creator)) {
            return Result.fail(401, "权限不足", null);
        }
        return  Result.success(memberService.getMember(target.getId()));
    }

    @ApiOperation("所在组织列表")
    @GetMapping("/list")
    public Result<List<ListDto>> list(@RequestParam Integer account) {
        var list = memberService.getOrganization(account);
        var result = new ArrayList<ListDto>();
        for (Organization item:
             list) {
            if (item.getDissolved() == 0) {
                var dto = new ListDto();
                BeanUtils.copyProperties(item, dto);
                result.add(dto);
            }
        }
        return Result.success(result);
    }

    @ApiOperation("创建的组织列表")
    @GetMapping("/manager")
    public Result<List<ListDto>> manager(@RequestParam Integer account) {
        var list = organizationService.list(new LambdaQueryWrapper<Organization>().eq(Organization::getCreator, account));
        var result = new ArrayList<ListDto>();
        for (Organization item:
                list) {
            if (item.getDissolved() == 0) {
                var dto = new ListDto();
                BeanUtils.copyProperties(item, dto);
                result.add(dto);
            }
        }
        return Result.success(result);
    }
}
