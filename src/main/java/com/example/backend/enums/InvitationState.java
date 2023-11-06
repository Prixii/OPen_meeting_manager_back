package com.example.backend.enums;


public enum InvitationState {
    WAITING("2"),
    ACCEPTED("1"),
    REFUSED("0");

    private String value;

    InvitationState(String value) {this.value = value;}

    public String getValue() {return this.value;}


}
