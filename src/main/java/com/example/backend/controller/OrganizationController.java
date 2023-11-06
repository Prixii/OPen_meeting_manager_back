package com.example.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.backend.bean.Result;
import com.example.backend.bean.vo.organization.*;
import com.example.backend.db.entity.Account;
import com.example.backend.db.entity.Organization;
import com.example.backend.db.service.AccountService;
import com.example.backend.db.service.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @ApiOperation("创建")
    @PostMapping("/create")
    public Result<Integer> create(@RequestBody CreateVo vo) {
        if (organizationService.isOrganizationExist(vo.getCreatorId(), vo.getName())){
          return Result.fail("组织名重复");
        }
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
        organizationService.getOne( new LambdaQueryWrapper<Organization>().eq(Organization::getId, vo.getGroupId()));
        if (target == null){
            return Result.fail("组织不存在");
        }
        if (!Objects.equals(target.getCreator(), vo.getCreatorId())) {
            return Result.fail(401, "权限不足", null);
        }
        target.setDissolved(true);
        organizationService.updateById(target);
        return Result.success("成功");
    }

    @ApiOperation("踢出")
    @PostMapping("/kick")
    public Result<Object> kick(@RequestBody KickVo vo) {
        Organization target =
                organizationService.getOne( new LambdaQueryWrapper<Organization>().eq(Organization::getId, vo.getGroupId()));
        if (target == null){
            return Result.fail("组织不存在");
        }
        if (!Objects.equals(target.getCreator(), vo.getCreatorId())) {
            return Result.fail(401, "权限不足", null);
        }
        Account account = accountService.getOne(new LambdaQueryWrapper<Account>().eq(Account::getId, vo.getAccountId()));
        if (account == null){
            return Result.fail(401, "用户不在组织内或不存在", null);
        }
        List<Account> members = target.getMember();
        members.remove(account);
        target.setMember(members);
        organizationService.updateById(target);
        return Result.success("成功");
    }

    @ApiOperation("退出")
    @PostMapping("/leave")
    public Result<Object> leave(@RequestBody LeaveVo vo) {
        Organization target =
                organizationService.getOne( new LambdaQueryWrapper<Organization>().eq(Organization::getId, vo.getGroupId()));
        if (target == null){
            return Result.fail("组织不存在");
        }
        Account account = accountService.getOne(new LambdaQueryWrapper<Account>().eq(Account::getId, vo.getAccountId()));
        List<Account> members = target.getMember();
        members.remove(account);
        organizationService.updateById(target);
        return Result.success("成功");
    }

    @ApiOperation("成员")
    @PostMapping("/member")
    public Result<List<Account>> member(@RequestBody MemberVo vo) {
        Organization target =
                organizationService.getOne( new LambdaQueryWrapper<Organization>().eq(Organization::getId, vo.getGroupId()));
        if (target == null){
            return Result.fail("组织不存在");
        }
        if (!Objects.equals(target.getCreator(), vo.getCreatorId())) {
            return Result.fail(401, "权限不足", null);
        }
        return  Result.success(target.getMember());
    }

    @ApiOperation("所在组织列表")
    @PostMapping("/list")
    public Result<List<Organization>> list(@RequestBody ListVo vo) {
        var organizationList = organizationService.list(new LambdaQueryWrapper<Organization>().eq(Organization::getCreator, vo.getAccountId()));
        return Result.success(organizationList);
    }
}
