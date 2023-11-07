package com.example.backend.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.bean.vo.account.RegisterVo;
import com.example.backend.db.entity.Account;
import com.example.backend.db.mapper.AccountMapper;
import com.example.backend.db.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
    @Override
    public boolean isUserExist(String phone) {
        return getOne(new LambdaQueryWrapper<Account>().eq(Account::getPhone, phone)) != null;
    }

    public Integer register(RegisterVo vo) {
        Account newAccount = new Account();
        Integer accountId = newAccount.init(vo);
        if (save(newAccount)) {
            return accountId;
        } else {
            return  -1;
        }
    }
}
