package com.tivit.talmatc.data.local.constant;

/**
 * Created by Alexzander Guillermo on 12/09/2017.
 */

public enum OrderEnum {

    FILTER_STATE("STATE"),
    STATE_CARGADO("ESTCAR"),
    STATE_EJECUTADO("EJEC"),
    STATE_EJECUTADO_DESCRIPCION("Ejecutado");

    private final String value;

    OrderEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
