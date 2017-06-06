package com.cears.serviceapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Dhiren on 4/17/2017.
 */

public class EquipmentTypeSubResponse implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("equipment_type")
    @Expose
    private String equipmentType;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
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
