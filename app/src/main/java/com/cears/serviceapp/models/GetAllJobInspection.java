package com.cears.serviceapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dhiren on 4/20/2017.
 */

public class GetAllJobInspection {

    @SerializedName("job_id")
    @Expose
    private String jobId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("equipment_type")
    @Expose
    private GetAllJobEquipmentType equipmentType;
    @SerializedName("service_type")
    @Expose
    private String serviceType;
    @SerializedName("job_ticket_no")
    @Expose
    private String jobTicketNo;
    @SerializedName("equipment_id")
    @Expose
    private String equipmentId;
    @SerializedName("site_id")
    @Expose
    private String siteId;
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("work_type")
    @Expose
    private String workType;
    @SerializedName("schedule_date")
    @Expose
    private String scheduleDate;
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
    @SerializedName("contract_type")
    @Expose
    private String contractType;
    @SerializedName("contract_id")
    @Expose
    private String contractId;
    @SerializedName("job_type")
    @Expose
    private String jobType;
    @SerializedName("schedule_id")
    @Expose
    private String scheduleId;
    @SerializedName("slotmsg")
    @Expose
    private String slotmsg;
    @SerializedName("is_deleted")
    @Expose
    private String isDeleted;
    @SerializedName("creation_datetime")
    @Expose
    private String creationDatetime;
    @SerializedName("modification_datetime")
    @Expose
    private String modificationDatetime;
    @SerializedName("deletion_datetime")
    @Expose
    private String deletionDatetime;
    @SerializedName("site")
    @Expose
    private GetAllJobSite site;
    @SerializedName("customer")
    @Expose
    private GetAllJobCustomer customer;

    @SerializedName("technician")
    @Expose
    private GetAllJobTechnician technician;

    @SerializedName("equipment")
    @Expose
    private GetAllJobEquipment equipment;

    public String get_long() {
        return _long;
    }

    public void set_long(String _long) {
        this._long = _long;
    }

    public GetAllJobEquipment getEquipment() {
        return equipment;
    }

    public void setEquipment(GetAllJobEquipment equipment) {
        this.equipment = equipment;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public GetAllJobEquipmentType getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(GetAllJobEquipmentType equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getJobTicketNo() {
        return jobTicketNo;
    }

    public void setJobTicketNo(String jobTicketNo) {
        this.jobTicketNo = jobTicketNo;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
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

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getSlotmsg() {
        return slotmsg;
    }

    public void setSlotmsg(String slotmsg) {
        this.slotmsg = slotmsg;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreationDatetime() {
        return creationDatetime;
    }

    public void setCreationDatetime(String creationDatetime) {
        this.creationDatetime = creationDatetime;
    }

    public String getModificationDatetime() {
        return modificationDatetime;
    }

    public void setModificationDatetime(String modificationDatetime) {
        this.modificationDatetime = modificationDatetime;
    }

    public String getDeletionDatetime() {
        return deletionDatetime;
    }

    public void setDeletionDatetime(String deletionDatetime) {
        this.deletionDatetime = deletionDatetime;
    }

    public GetAllJobSite getSite() {
        return site;
    }

    public void setSite(GetAllJobSite site) {
        this.site = site;
    }

    public GetAllJobCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(GetAllJobCustomer customer) {
        this.customer = customer;
    }

    public GetAllJobTechnician getTechnician() {
        return technician;
    }

    public void setTechnician(GetAllJobTechnician technician) {
        this.technician = technician;
    }


}
