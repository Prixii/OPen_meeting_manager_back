package com.example.backend.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.bean.vo.RegisterVo;
import com.example.backend.db.entity.Account;
import com.example.backend.db.mapper.AccountMapper;
import com.example.backend.db.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author prixii
 * @date 2023/11/6 13:51
 */

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
    @Override
    public boolean isUserExist(String phone) {
        return getOne(new LambdaQueryWrapper<Account>().eq(Account::getPhone, phone)) != null;
    }

    public Integer register(RegisterVo vo) {
        Account newAccount = new Account();
        BeanUtils.copyProperties(vo, newAccount);
        System.out.println(newAccount);
        Integer accountId = newAccount.setId();
        if (save(newAccount)) {
            return accountId;
        } else {
            return  -1;
        }
    }
}
