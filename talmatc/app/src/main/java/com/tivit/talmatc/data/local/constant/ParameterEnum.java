package com.tivit.talmatc.data.local.constant;

/**
 * Created by Alexzander Guillermo on 15/09/2017.
 */

public enum ParameterEnum {

    SITUACION("SITUACION"),
    ACCION("ACCION");

    private final String value;

    ParameterEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
