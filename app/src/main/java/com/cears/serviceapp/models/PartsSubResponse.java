package com.cears.serviceapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dhiren on 4/18/2017.
 */

public class PartsSubResponse {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("part_id")
    @Expose
    private String partId;
    @SerializedName("part_manufacturer")
    @Expose
    private String partManufacturer;
    @SerializedName("part_description")
    @Expose
    private String partDescription;
    @SerializedName("part_unit_of_measure")
    @Expose
    private String partUnitOfMeasure;
    @SerializedName("part_category")
    @Expose
    private String partCategory;
    @SerializedName("part_sub_category")
    @Expose
    private String partSubCategory;
    @SerializedName("section_type")
    @Expose
    private String sectionType;
    @SerializedName("sa_manufacturer")
    @Expose
    private String saManufacturer;
    @SerializedName("sa_model")
    @Expose
    private String saModel;
    @SerializedName("eqpt_type")
    @Expose
    private String eqptType;
    @SerializedName("eqpt_manufacturer")
    @Expose
    private String eqptManufacturer;
    @SerializedName("manufacturer_model")
    @Expose
    private String manufacturerModel;
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
    @SerializedName("unit_of_measure")
    @Expose
    private String unitOfMeasure;
    @SerializedName("manufacturer_name")
    @Expose
    private String manufacturerName;
    @SerializedName("sub_category_name")
    @Expose
    private String subCategoryName;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("section_name")
    @Expose
    private String sectionName;
    @SerializedName("equipment_type")
    @Expose
    private String equipmentType;
    @SerializedName("sa_mfgr_name")
    @Expose
    private String saMfgrName;
    @SerializedName("equipment_mfgr_name")
    @Expose
    private String equipmentMfgrName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getPartManufacturer() {
        return partManufacturer;
    }

    public void setPartManufacturer(String partManufacturer) {
        this.partManufacturer = partManufacturer;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public void setPartDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    public String getPartUnitOfMeasure() {
        return partUnitOfMeasure;
    }

    public void setPartUnitOfMeasure(String partUnitOfMeasure) {
        this.partUnitOfMeasure = partUnitOfMeasure;
    }

    public String getPartCategory() {
        return partCategory;
    }

    public void setPartCategory(String partCategory) {
        this.partCategory = partCategory;
    }

    public String getPartSubCategory() {
        return partSubCategory;
    }

    public void setPartSubCategory(String partSubCategory) {
        this.partSubCategory = partSubCategory;
    }

    public String getSectionType() {
        return sectionType;
    }

    public void setSectionType(String sectionType) {
        this.sectionType = sectionType;
    }

    public String getSaManufacturer() {
        return saManufacturer;
    }

    public void setSaManufacturer(String saManufacturer) {
        this.saManufacturer = saManufacturer;
    }

    public String getSaModel() {
        return saModel;
    }

    public void setSaModel(String saModel) {
        this.saModel = saModel;
    }

    public String getEqptType() {
        return eqptType;
    }

    public void setEqptType(String eqptType) {
        this.eqptType = eqptType;
    }

    public String getEqptManufacturer() {
        return eqptManufacturer;
    }

    public void setEqptManufacturer(String eqptManufacturer) {
        this.eqptManufacturer = eqptManufacturer;
    }

    public String getManufacturerModel() {
        return manufacturerModel;
    }

    public void setManufacturerModel(String manufacturerModel) {
        this.manufacturerModel = manufacturerModel;
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

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getSaMfgrName() {
        return saMfgrName;
    }

    public void setSaMfgrName(String saMfgrName) {
        this.saMfgrName = saMfgrName;
    }

    public String getEquipmentMfgrName() {
        return equipmentMfgrName;
    }

    public void setEquipmentMfgrName(String equipmentMfgrName) {
        this.equipmentMfgrName = equipmentMfgrName;
    }

}
