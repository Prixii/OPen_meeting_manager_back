package com.example.backend.db.entity;

import com.example.backend.enums.InvitationState;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.sql.Time;
import java.util.Date;

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

    public void generateId() { id = (organization.toString() + account.toString() + new Date(System.currentTimeMillis())).hashCode();}

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
