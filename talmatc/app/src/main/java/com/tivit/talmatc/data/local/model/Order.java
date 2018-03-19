package com.tivit.talmatc.data.local.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

/**
 * Created by Alexzander Guillermo on 04/09/2017.
 */

public class Order extends BaseModel implements Parcelable {

    // ATTRIBUTES
    private String code;
    private Long assignationDate;
    private Long creationDate;
    private Boolean enabled;

    @Expose(deserialize = false, serialize = false)
    private String username;

    private String subTypeDescription;

    private String statusCode;
    private String statusDescription;

    private String customerCode;
    private String customerDocumentNumber;
    private String customerPhone;
    private String customerMobilePhone;
    private String customerFullname;

    private String customerDocumentTypeDescription;

    private String customerAddressDescription;
    private String customerAddressReferencia;
    private Double customerAddressLatitude;
    private Double customerAddressLongitude;
    private String customerAddressDistrictDescription;
    private String customerAddressProvinceDescription;

    // RELATIONSHIPS
    @Expose(deserialize = false)
    private ReconnectionCut reconnectionCut;

    // CONSTRUCTORS
    public Order() {
    }

    // GETTERS - SETTERS
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getAssignationDate() {
        return assignationDate;
    }

    public void setAssignationDate(Long assignationDate) {
        this.assignationDate = assignationDate;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubTypeDescription() {
        return subTypeDescription;
    }

    public void setSubTypeDescription(String subTypeDescription) {
        this.subTypeDescription = subTypeDescription;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerDocumentNumber() {
        return customerDocumentNumber;
    }

    public void setCustomerDocumentNumber(String customerDocumentNumber) {
        this.customerDocumentNumber = customerDocumentNumber;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerMobilePhone() {
        return customerMobilePhone;
    }

    public void setCustomerMobilePhone(String customerMobilePhone) {
        this.customerMobilePhone = customerMobilePhone;
    }

    public String getCustomerFullname() {
        return customerFullname;
    }

    public void setCustomerFullname(String customerFullname) {
        this.customerFullname = customerFullname;
    }

    public String getCustomerDocumentTypeDescription() {
        return customerDocumentTypeDescription;
    }

    public void setCustomerDocumentTypeDescription(String customerDocumentTypeDescription) {
        this.customerDocumentTypeDescription = customerDocumentTypeDescription;
    }

    public String getCustomerAddressDescription() {
        return customerAddressDescription;
    }

    public void setCustomerAddressDescription(String customerAddressDescription) {
        this.customerAddressDescription = customerAddressDescription;
    }

    public String getCustomerAddressReferencia() {
        return customerAddressReferencia;
    }

    public void setCustomerAddressReferencia(String customerAddressReferencia) {
        this.customerAddressReferencia = customerAddressReferencia;
    }

    public Double getCustomerAddressLatitude() {
        return customerAddressLatitude;
    }

    public void setCustomerAddressLatitude(Double customerAddressLatitude) {
        this.customerAddressLatitude = customerAddressLatitude;
    }

    public Double getCustomerAddressLongitude() {
        return customerAddressLongitude;
    }

    public void setCustomerAddressLongitude(Double customerAddressLongitude) {
        this.customerAddressLongitude = customerAddressLongitude;
    }

    public String getCustomerAddressDistrictDescription() {
        return customerAddressDistrictDescription;
    }

    public void setCustomerAddressDistrictDescription(String customerAddressDistrictDescription) {
        this.customerAddressDistrictDescription = customerAddressDistrictDescription;
    }

    public String getCustomerAddressProvinceDescription() {
        return customerAddressProvinceDescription;
    }

    public void setCustomerAddressProvinceDescription(String customerAddressProvinceDescription) {
        this.customerAddressProvinceDescription = customerAddressProvinceDescription;
    }

    public ReconnectionCut getReconnectionCut() {
        return reconnectionCut;
    }

    public void setReconnectionCut(ReconnectionCut reconnectionCut) {
        this.reconnectionCut = reconnectionCut;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeValue(this.assignationDate);
        dest.writeValue(this.creationDate);
        dest.writeValue(this.enabled);
        dest.writeString(this.username);
        dest.writeString(this.subTypeDescription);
        dest.writeString(this.statusCode);
        dest.writeString(this.statusDescription);
        dest.writeString(this.customerCode);
        dest.writeString(this.customerDocumentNumber);
        dest.writeString(this.customerPhone);
        dest.writeString(this.customerMobilePhone);
        dest.writeString(this.customerFullname);
        dest.writeString(this.customerDocumentTypeDescription);
        dest.writeString(this.customerAddressDescription);
        dest.writeString(this.customerAddressReferencia);
        dest.writeValue(this.customerAddressLatitude);
        dest.writeValue(this.customerAddressLongitude);
        dest.writeString(this.customerAddressDistrictDescription);
        dest.writeString(this.customerAddressProvinceDescription);
        dest.writeParcelable(this.reconnectionCut, flags);
    }

    protected Order(Parcel in) {
        this.code = in.readString();
        this.assignationDate = (Long) in.readValue(Long.class.getClassLoader());
        this.creationDate = (Long) in.readValue(Long.class.getClassLoader());
        this.enabled = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.username = in.readString();
        this.subTypeDescription = in.readString();
        this.statusCode = in.readString();
        this.statusDescription = in.readString();
        this.customerCode = in.readString();
        this.customerDocumentNumber = in.readString();
        this.customerPhone = in.readString();
        this.customerMobilePhone = in.readString();
        this.customerFullname = in.readString();
        this.customerDocumentTypeDescription = in.readString();
        this.customerAddressDescription = in.readString();
        this.customerAddressReferencia = in.readString();
        this.customerAddressLatitude = (Double) in.readValue(Double.class.getClassLoader());
        this.customerAddressLongitude = (Double) in.readValue(Double.class.getClassLoader());
        this.customerAddressDistrictDescription = in.readString();
        this.customerAddressProvinceDescription = in.readString();
        this.reconnectionCut = in.readParcelable(ReconnectionCut.class.getClassLoader());
    }

    public static final Parcelable.Creator<Order> CREATOR = new Parcelable.Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel source) {
            return new Order(source);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

}
