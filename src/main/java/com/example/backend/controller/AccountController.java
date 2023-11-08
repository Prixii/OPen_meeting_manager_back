package com.example.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.backend.bean.Result;
import com.example.backend.bean.dto.account.SearchDto;
import com.example.backend.bean.vo.account.LoginVo;
import com.example.backend.bean.vo.account.RegisterVo;
import com.example.backend.db.entity.Account;
import com.example.backend.db.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("account")
@Api( tags = "Account")
public class AccountController {
    @Resource
    AccountService service;

    @ApiOperation("注册")
    @PostMapping("/register")
    public Result<Integer> register(@RequestBody RegisterVo vo) {
        if(service.isUserExist(vo.getPhone())){
            return  Result.fail("该手机号已被注册");
        }

        Integer accountId = service.register(vo);
        if (accountId != -1){
            return  Result.success(accountId);
        } else {
            return  Result.fail("未知错误！保存异常");
        }
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result<Integer> login(@RequestBody LoginVo vo) {
        Account targetAccount = service.getOne(new LambdaQueryWrapper<Account>().eq(Account::getPhone, vo.getPhone()));
        if (targetAccount == null) {
            return Result.fail("用户不存在");
        }

        if (Objects.equals(vo.getPassword(), targetAccount.getPassword())) {
            return Result.success(targetAccount.getId());
        } else {
            return Result.fail(401, "密码错误", -1);
        }
    }

    @ApiOperation("搜索")
    @GetMapping("/search")
    public Result<SearchDto> login(@RequestParam String phone ) {
        var result = service.getByPhone(phone);
        if (result == null) {
            return Result.fail("对象不存在");
        } else {
            var searchDto = new SearchDto();
            BeanUtils.copyProperties(result, searchDto);
            return Result.success(searchDto);
        }

    }

    @Deprecated
    @GetMapping("/list")
    public Result<List<Account>> list() {
        return Result.success(service.list());
    }
}
