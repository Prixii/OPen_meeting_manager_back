package com.example.backend.db.entity;

import com.example.backend.enums.InvitationState;
import com.example.backend.util.CommonUtil;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class Invitation {
    private Integer id;
    private Integer organization;
    private Integer account;
    private String state;

    public void accept() {
        state = InvitationState.ACCEPTED.getValue();
    }

    public void refuse() {
        state = InvitationState.REFUSED.getValue();
    }

    public void generateId() { id = (organization.toString() + account.toString() + CommonUtil.randomize()).hashCode();}

    public void initState() {state = InvitationState.WAITING.getValue();}

    public void init(Object src) {
        if (src != null){
            BeanUtils.copyProperties(src, this);
        }
        generateId();
        initState();
    }

    public boolean belongTo(int account) {return account == this.account;}

}
