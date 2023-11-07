package com.example.backend.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.bean.vo.account.RegisterVo;
import com.example.backend.db.entity.Account;


public interface AccountService extends IService<Account> {
    boolean isUserExist(String phone);
    Integer register(RegisterVo vo);
    Account getByPhone(String phone);
}
