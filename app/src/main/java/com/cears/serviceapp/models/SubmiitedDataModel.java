package com.cears.serviceapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Dhiren on 4/18/2017.
 */

public class SubmiitedDataModel {


    @SerializedName("equipment_type")
    @Expose
    private String equipmentType;
    @SerializedName("service_type")
    @Expose
    private String serviceType;
    @SerializedName("equipment_no")
    @Expose
    private String equipmentNo;
    @SerializedName("site_name")
    @Expose
    private String siteName;
    @SerializedName("customer_name")
    @Expose
    private String customerName;
    @SerializedName("customer_contact_name")
    @Expose
    private String customerContactName;
    @SerializedName("customer_contact_no")
    @Expose
    private String customerContactNo;
    @SerializedName("work_type")
    @Expose
    private String workType;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("long")
    @Expose
    private String _long;
    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("signature")
    @Expose
    private String signature;
    @SerializedName("pre_answer")
    @Expose
    private ArrayList<SubmittedPreAnswer> preAnswer = null;

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getEquipmentNo() {
        return equipmentNo;
    }

    public void setEquipmentNo(String equipmentNo) {
        this.equipmentNo = equipmentNo;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerContactName() {
        return customerContactName;
    }

    public void setCustomerContactName(String customerContactName) {
        this.customerContactName = customerContactName;
    }

    public String getCustomerContactNo() {
        return customerContactNo;
    }

    public void setCustomerContactNo(String customerContactNo) {
        this.customerContactNo = customerContactNo;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLong() {
        return _long;
    }

    public void setLong(String _long) {
        this._long = _long;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public ArrayList<SubmittedPreAnswer> getPreAnswer() {
        return preAnswer;
    }

    public void setPreAnswer(ArrayList<SubmittedPreAnswer> preAnswer) {
        this.preAnswer = preAnswer;
    }
}
