package com.cears.serviceapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Dhiren on 4/28/2017.
 */

public class QuestionsEquipmentType implements Serializable {


    @SerializedName("equipment_id")
    @Expose
    private String equipmentId;
    @SerializedName("value")
    @Expose
    private String value;

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
