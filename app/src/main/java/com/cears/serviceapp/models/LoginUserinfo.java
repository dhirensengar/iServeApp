package com.cears.serviceapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dhiren on 4/13/2017.
 */

public class LoginUserinfo {

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
    private Object deletionDatetime;

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

    public Object getDeletionDatetime() {
        return deletionDatetime;
    }

    public void setDeletionDatetime(Object deletionDatetime) {
        this.deletionDatetime = deletionDatetime;
    }

}
