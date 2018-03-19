package com.tivit.talmatc.data.local.model;

import com.google.gson.annotations.Expose;

/**
 * Created by Alexzander Guillermo on 08/09/2017.
 */

public abstract class BaseModel {

    @Expose(deserialize = false, serialize = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
