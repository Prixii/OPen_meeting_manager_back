package com.example.backend.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.bean.Result;
import com.example.backend.bean.vo.organization.CreateVo;
import com.example.backend.db.entity.Organization;
import com.example.backend.db.mapper.OrganizationMapper;
import com.example.backend.db.service.OrganizationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author prixii
 * @date 2023/11/6 15:12
 */

@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {
    @Override
    public boolean isOrganizationExist(Integer creatorId, String name) {
        return getOne(new LambdaQueryWrapper<Organization>().eq(Organization::getCreator, creatorId).eq(Organization::getName, name)) != null;
    }

    @Override
    public Integer create(CreateVo vo) {
        Organization newOrganization = new Organization();
        BeanUtils.copyProperties(vo, newOrganization);
        Integer organizationId = newOrganization.generateId();
        if (save(newOrganization)) {
            return organizationId;
        }
        return -1;
    }

    @Override
    public boolean isCreator(Integer account, Integer organization) {
        return getOne(new LambdaQueryWrapper<Organization>().eq(Organization::getCreator, account).eq(Organization::getId, organization)) != null;
    }
}
