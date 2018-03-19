package com.tivit.talmatc.data.local.model;

import com.google.gson.annotations.Expose;

/**
 * Created by Alexzander Guillermo on 05/09/2017.
 */

public class Parameter extends BaseModel{

    // ATTRIBUTES
    private String code;
    private String description;

    @Expose(deserialize = false)
    private String entidad;

    // RELATIONSHIPS

    // CONSTRUCTORS
    public Parameter() {
    }

    // GETTERS - SETTERS
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }
}
