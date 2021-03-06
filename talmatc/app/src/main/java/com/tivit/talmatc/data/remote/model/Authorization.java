package com.tivit.talmatc.data.remote.model;

import com.tivit.talmatc.data.local.model.User;

import java.util.List;

/**
 * Created by Alexzander Guillermo on 26/08/2017.
 */

public class Authorization {

    private List<String> authorities;
    private String accessToken;
    private String refreshToken;
    private String username;
    private User user;

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
