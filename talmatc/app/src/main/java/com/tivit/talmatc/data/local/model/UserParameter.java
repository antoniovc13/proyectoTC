package com.tivit.talmatc.data.local.model;

/**
 * Created by Antonio.Valdivieso on 19/03/2018.
 */

public class UserParameter extends BaseModel {

    private String typeFlight;
    private String vehicleType;
    private String vehicleValue;


    public String getTypeFlight() {
        return typeFlight;
    }

    public void setTypeFlight(String typeFlight) {
        this.typeFlight = typeFlight;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleValue() {
        return vehicleValue;
    }

    public void setVehicleValue(String vehicleValue) {
        this.vehicleValue = vehicleValue;
    }
}
