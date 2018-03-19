package com.tivit.talmatc.data.local.model;

/**
 * Created by Alexzander Guillermo on 06/09/2017.
 */

public class User extends BaseModel {

    // ATTRIBUTES
    private String username;
    private String password;
    private String fullName;
    private String role;
    private String unidad;

    // RELATIONSHIPS

    // CONSTRUCTORS
    public User() {
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    // GETTERS - SETTERS


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}
