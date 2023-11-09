package com.example.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.backend.bean.Result;
import com.example.backend.bean.dto.invitation.ListDto;
import com.example.backend.bean.vo.invitation.AcceptVo;
import com.example.backend.bean.vo.invitation.CreateVo;
import com.example.backend.bean.vo.invitation.RefuseVo;
import com.example.backend.db.entity.Invitation;
import com.example.backend.db.service.AccountService;
import com.example.backend.db.service.InvitationService;
import com.example.backend.db.service.MemberService;
import com.example.backend.db.service.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("invitation")
@Api(tags ="Invitation")
public class InvitationController {

    @Resource
    InvitationService invitationService;

    @Resource
    OrganizationService organizationService;

    @Resource
    AccountService accountService;

    @Resource
    MemberService memberService;

    @ApiOperation("邀请列表")
    @GetMapping("/list")
    Result<List<ListDto>> list(@RequestParam Integer account) {
        var invitations =invitationService.list(new LambdaQueryWrapper<Invitation>().eq(Invitation::getAccount, account));
        var records = new ArrayList<ListDto>();
        for (Invitation invitation:
             invitations) {
            var listDto = new ListDto();
            var organization = organizationService.getById(invitation.getOrganization());
            BeanUtils.copyProperties(invitation, listDto);
            listDto.setOrganizationName(organization.getName());
            records.add(listDto);
        }
        return Result.success(records);
    }

    @ApiOperation("发起邀请")
    @PostMapping("/create")
    Result<Object> create(@RequestBody CreateVo vo) {
        if (organizationService.isCreator(vo.getCreator(), vo.getOrganization())){
            Invitation newInvitation = new Invitation();
            var account = accountService.getByPhone(vo.getPhone());
            newInvitation.init(vo, account.getId());
            if (invitationService.save(newInvitation)) {
                return Result.success("邀请成功");
            } else {
                return Result.fail("");
            }
        }
        return Result.fail(401, "权限不足", null);
    }

    @ApiOperation("接受邀请")
    @PostMapping("/accept")
    Result<Object> accept(@RequestBody AcceptVo vo) {
        Invitation invitation = invitationService.getById(vo.getInvitation());
        if (invitation.belongTo(vo.getAccount())) {
            invitation.accept();
            memberService.addMember(invitation.getAccount(),invitation.getOrganization());
            if (invitationService.updateById(invitation)){
                return Result.success("接受成功");
            } else {
                return Result.fail("");
            }
        }
        return Result.fail(401, "权限不足", null);
    }

    @ApiOperation("拒绝邀请")
    @PostMapping("/refuse")
    Result<Object> refuse(@RequestBody RefuseVo vo){
        Invitation invitation = invitationService.getById(vo.getInvitation());
        if (invitation.belongTo(vo.getAccount())) {
            invitation.refuse();
            if (invitationService.updateById(invitation)){
                return Result.success("拒绝成功");
            } else {
                return Result.fail("");
            }
        }
        return Result.fail(401, "权限不足", null);
    }
}
