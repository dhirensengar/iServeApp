package com.cears.serviceapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Dhiren on 5/8/2017.
 */

public class GetAllJob_Jobs implements Serializable {

    @SerializedName("job_id")
    @Expose
    private String jobId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("equipment_type")
    @Expose
    private EquipmentTypeSubResponse equipmentType;
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
    @SerializedName("admin_id")
    @Expose
    private String adminId;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email_id")
    @Expose
    private String emailId;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("role_group")
    @Expose
    private String roleGroup;
    @SerializedName("customer")
    @Expose
    private GetAllJobCustomer customer;
    @SerializedName("technician")
    @Expose
    private GetAllJobTechnician technician;
    @SerializedName("equipment")
    @Expose
    private GetAllJobEquipment equipment;

    private String all_jobs_date;
    private String all_job_ans;
    private String all_jobs_state;

    public ArrayList<QuestionSubResponse> getQuestionanslist() {
        return questionanslist;
    }

    public void setQuestionanslist(ArrayList<QuestionSubResponse> questionanslist) {
        this.questionanslist = questionanslist;
    }

    private ArrayList<QuestionSubResponse> questionanslist;

    public String get_long() {
        return _long;
    }

    public void set_long(String _long) {
        this._long = _long;
    }

    public String getAll_jobs_date() {
        return all_jobs_date;
    }

    public void setAll_jobs_date(String all_jobs_date) {
        this.all_jobs_date = all_jobs_date;
    }

    public String getAll_job_ans() {
        return all_job_ans;
    }

    public void setAll_job_ans(String all_job_ans) {
        this.all_job_ans = all_job_ans;
    }

    public String getAll_jobs_state() {
        return all_jobs_state;
    }

    public void setAll_jobs_state(String all_jobs_state) {
        this.all_jobs_state = all_jobs_state;
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

    public EquipmentTypeSubResponse getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(EquipmentTypeSubResponse equipmentType) {
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

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoleGroup() {
        return roleGroup;
    }

    public void setRoleGroup(String roleGroup) {
        this.roleGroup = roleGroup;
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

    public GetAllJobEquipment getEquipment() {
        return equipment;
    }

    public void setEquipment(GetAllJobEquipment equipment) {
        this.equipment = equipment;
    }

}
