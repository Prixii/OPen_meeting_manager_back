package com.example.backend;

import com.example.backend.bean.vo.organization.CreateVo;
import com.example.backend.controller.OrganizationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackEndApplicationTests {
    @Autowired
    private OrganizationController organizationController;

    @Test
    void organizationApi() {
        CreateVo vo = new CreateVo();
        vo.setCreator(1449621058);
        vo.setName("Test2");
        assert organizationController.create(vo).getCode() == 200;

    }

}
