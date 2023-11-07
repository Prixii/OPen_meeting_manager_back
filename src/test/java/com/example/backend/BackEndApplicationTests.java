package com.example.backend;

import com.example.backend.bean.vo.account.LoginVo;
import com.example.backend.bean.vo.account.RegisterVo;
import com.example.backend.bean.vo.organization.CreateVo;
import com.example.backend.controller.AccountController;
import com.example.backend.controller.InvitationController;
import com.example.backend.controller.MeetingController;
import com.example.backend.controller.OrganizationController;
import com.example.backend.util.CommonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackEndApplicationTests {
    @Autowired
    private AccountController accountController;

    @Autowired
    private InvitationController invitationController;

    @Autowired
    private MeetingController meetingController;

    @Autowired
    private OrganizationController organizationController;

    Integer accountId = 1641936606;

    @Test
    void register() {
        RegisterVo vo = new RegisterVo();
        vo.setName("testName");
        vo.setPassword("123456");
        vo.setPhone("13649856789");
        var result = accountController.register(vo);
        assert result.getCode() == 200;
        System.out.println(result.getData());
    }

    @Test
    void login() {
        String phone = "13649856789";
        String pwd = "123456";
        var vo = new LoginVo();
        vo.setPassword(pwd);
        vo.setPhone(phone);
        var result = accountController.login(vo);
        assert result.getCode() == 200;
    }

    @Test
    void loginWrongPwd() {
        String phone = "13649856789";
        String pwd = "12345688";
        var vo = new LoginVo();
        vo.setPassword(pwd);
        vo.setPhone(phone);
        var result = accountController.login(vo);
        assert result.getCode() == 401;
    }

    @Test
    void loginWrongPhone() {
        String phone = "136498567859";
        String pwd = "123456";
        var vo = new LoginVo();
        vo.setPassword(pwd);
        vo.setPhone(phone);
        var result = accountController.login(vo);
        assert result.getCode() == 400;
    }

    @Test
    void organizationTest() {
        CreateVo vo = new CreateVo();
        vo.setCreator(1449621058);
        vo.setName("Test2");
        assert organizationController.create(vo).getCode() == 200;
    }

    @Test
    void loginTest() {
        login();
        loginWrongPwd();
        loginWrongPhone();
    }

    @Test
    void testTime() {
        System.out.println(CommonUtil.randomize());
        System.out.println(CommonUtil.randomize());
        System.out.println(CommonUtil.randomize());
    }

}
