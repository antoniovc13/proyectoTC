package com.tivit.talmatc.data.local.constant;

/**
 * Created by Alexzander Guillermo on 15/09/2017.
 */

public enum RoleEnum {

    ROL1("ROL1"),
    ROL2("ROL2"),
    ROL3("ROL3");

    private final String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
