package com.tivit.talmatc.data.remote.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Alexzander Guillermo on 26/08/2017.
 */

public class Login {

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
