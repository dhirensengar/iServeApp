package com.cears.serviceapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Dhiren on 4/15/2017.
 */

public class QuestionSubResponse implements Serializable {

    @SerializedName("question_id")
    @Expose
    private String questionId;
    @SerializedName("section_id")
    @Expose
    private String sectionId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("major")
    @Expose
    private String major;
    @SerializedName("minor")
    @Expose
    private String minor;
    @SerializedName("cleaning")
    @Expose
    private String cleaning;
    @SerializedName("R1")
    @Expose
    private String r1;
    @SerializedName("R2")
    @Expose
    private String r2;
    @SerializedName("R3")
    @Expose
    private String r3;
    @SerializedName("R4")
    @Expose
    private String r4;
    @SerializedName("R5")
    @Expose
    private String r5;
    @SerializedName("value")
    @Expose
    private String value;
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
    @SerializedName("equipment_types")
    @Expose
    private ArrayList<QuestionsEquipmentType> equipmentTypes = null;

    @SerializedName("isSkipped")
    @Expose
    private String Skipped = "no";

    @SerializedName("isRepaired")
    @Expose
    private String Answer = "";

    private String additionalText;

    public String getAdditionalText() {
        return additionalText;
    }

    public void setAdditionalText(String additionalText) {
        this.additionalText = additionalText;
    }

    private String partDescription = "";
    private String partNumber = "";
    private int checkedPosition;
    private String manPower, artDescription, partQuantity, optionText;
    String startDate, endTime;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getOptionText() {
        return optionText;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getManPower() {
        return manPower;
    }

    public void setManPower(String manPower) {
        this.manPower = manPower;
    }

    public int getCheckedPosition() {
        return checkedPosition;
    }

    public void setCheckedPosition(int checkedPosition) {
        this.checkedPosition = checkedPosition;
    }

    public String getArtDescription() {
        return artDescription;
    }

    public void setArtDescription(String artDescription) {
        this.artDescription = artDescription;
    }

    public String getPartQuantity() {
        return partQuantity;
    }

    public void setPartQuantity(String partQuantity) {
        this.partQuantity = partQuantity;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public void setPartDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public String getSkipped() {
        return Skipped;
    }

    public void setSkipped(String skipped) {
        Skipped = skipped;
    }


    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public String getCleaning() {
        return cleaning;
    }

    public void setCleaning(String cleaning) {
        this.cleaning = cleaning;
    }

    public String getR1() {
        return r1;
    }

    public void setR1(String r1) {
        this.r1 = r1;
    }

    public String getR2() {
        return r2;
    }

    public void setR2(String r2) {
        this.r2 = r2;
    }

    public String getR3() {
        return r3;
    }

    public void setR3(String r3) {
        this.r3 = r3;
    }

    public String getR4() {
        return r4;
    }

    public void setR4(String r4) {
        this.r4 = r4;
    }

    public String getR5() {
        return r5;
    }

    public void setR5(String r5) {
        this.r5 = r5;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public ArrayList<QuestionsEquipmentType> getEquipmentTypes() {
        return equipmentTypes;
    }

    public void setEquipmentTypes(ArrayList<QuestionsEquipmentType> equipmentTypes) {
        this.equipmentTypes = equipmentTypes;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }


}
