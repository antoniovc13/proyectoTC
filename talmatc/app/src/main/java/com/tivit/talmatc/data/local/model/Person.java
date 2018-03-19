package com.tivit.talmatc.data.local.model;

/**
 * Created by Alexzander Guillermo on 06/09/2017.
 */

public class Person extends BaseModel {

    // ATTRIBUTES
    private String numeroDocum;
    private String nombre;
    private String apellidoPat;
    private String apellidoMat;
    private String fullName;
    private String phone;
    private String mobile_phone;
    private String email;

    // RELATIONSHIPS

    // CONSTRUCTORS
    public Person() {
    }

    // GETTERS - SETTERS
    public String getNumeroDocum() {
        return numeroDocum;
    }

    public void setNumeroDocum(String numeroDocum) {
        this.numeroDocum = numeroDocum;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPat() {
        return apellidoPat;
    }

    public void setApellidoPat(String apellidoPat) {
        this.apellidoPat = apellidoPat;
    }

    public String getApellidoMat() {
        return apellidoMat;
    }

    public void setApellidoMat(String apellidoMat) {
        this.apellidoMat = apellidoMat;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
