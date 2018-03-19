package com.tivit.talmatc.data.local.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by Alexzander Guillermo on 08/09/2017.
 */

public class ReconnectionCut extends BaseModel implements Parcelable {

    // ATTRIBUTES
    private String orderCode;

    private String situationCode;
    private String situationDescription;

    private String actionCode;
    private String actionDescription;

    private Double measurement;
    private String observation;
    private String picture;

    private Long creationDate;

    // RELATIONSHIPS
    @Expose(deserialize = false, serialize = false)
    private List<Archivo> archivos;

    // CONSTRUCTORS
    public ReconnectionCut() {
    }

    // GETTERS - SETTERS
    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getSituationCode() {
        return situationCode;
    }

    public void setSituationCode(String situationCode) {
        this.situationCode = situationCode;
    }

    public String getSituationDescription() {
        return situationDescription;
    }

    public void setSituationDescription(String situationDescription) {
        this.situationDescription = situationDescription;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
    }

    public Double getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Double measurement) {
        this.measurement = measurement;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public List<Archivo> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<Archivo> archivos) {
        this.archivos = archivos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.orderCode);
        dest.writeString(this.situationCode);
        dest.writeString(this.situationDescription);
        dest.writeString(this.actionCode);
        dest.writeString(this.actionDescription);
        dest.writeValue(this.measurement);
        dest.writeString(this.observation);
        dest.writeString(this.picture);
        dest.writeValue(this.creationDate);
        dest.writeTypedList(this.archivos);
    }

    protected ReconnectionCut(Parcel in) {
        this.orderCode = in.readString();
        this.situationCode = in.readString();
        this.situationDescription = in.readString();
        this.actionCode = in.readString();
        this.actionDescription = in.readString();
        this.measurement = (Double) in.readValue(Double.class.getClassLoader());
        this.observation = in.readString();
        this.picture = in.readString();
        this.creationDate = (Long) in.readValue(Long.class.getClassLoader());
        this.archivos = in.createTypedArrayList(Archivo.CREATOR);
    }

    public static final Creator<ReconnectionCut> CREATOR = new Creator<ReconnectionCut>() {
        @Override
        public ReconnectionCut createFromParcel(Parcel source) {
            return new ReconnectionCut(source);
        }

        @Override
        public ReconnectionCut[] newArray(int size) {
            return new ReconnectionCut[size];
        }
    };
}
