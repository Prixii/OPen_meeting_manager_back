package com.example.backend.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.bean.vo.organization.CreateVo;
import com.example.backend.db.entity.Organization;


public interface OrganizationService extends IService<Organization> {
    boolean isOrganizationExist(Integer creatorId, String name);
    Integer create(CreateVo vo);
    boolean isCreator(Integer account, Integer organization);
}
