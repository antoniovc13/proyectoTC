package com.tivit.talmatc.data.local.constant;

/**
 * Created by Alexzander Guillermo on 15/09/2017.
 */

public enum ParameterEnum {

    POINT_INIT("POINT_INIT"),
    CAMION("CAMION"),
    TRACTOR("TRACTOR");

    private final String value;

    ParameterEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
