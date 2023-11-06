package com.example.backend.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.db.entity.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
