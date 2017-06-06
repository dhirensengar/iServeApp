package com.cears.serviceapp.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by VIKAS SAHU on 21/12/16.
 */

public class DatabasePojo implements Parcelable {

    String id, question, equipmentType, serviceType, Group, isPhoto, questionType, signatureLink, additionalTextFiled, name, code, optionText, photoLink, equipNumber, jobTicketNumber;
    String preAnswer, dateString, unit, customerName, techName, siteName, unitFixed;
    String partDescription, partNumber, partQuantity, manPower, isRepaired, surveyStartTime, geoLat, geoLang;
    String submitted;

    int optionCount;

    boolean isAnswered;

    public DatabasePojo() {

    }

    public String getIsRepaired() {
        return isRepaired;
    }

    public void setIsRepaired(String isRepaired) {
        this.isRepaired = isRepaired;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public void setPartDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPartQuantity() {
        return partQuantity;
    }

    public void setPartQuantity(String partQuantity) {
        this.partQuantity = partQuantity;
    }

    public String getManPower() {
        return manPower;
    }

    public void setManPower(String manPower) {
        this.manPower = manPower;
    }

    public String getSurveyStartTime() {
        return surveyStartTime;
    }

    public void setSurveyStartTime(String surveyStartTime) {
        this.surveyStartTime = surveyStartTime;
    }

    public String getGeoLat() {
        return geoLat;
    }

    public void setGeoLat(String geoLat) {
        this.geoLat = geoLat;
    }

    public String getGeoLang() {
        return geoLang;
    }

    public void setGeoLang(String geoLang) {
        this.geoLang = geoLang;
    }

    public DatabasePojo(Parcel in) {
        id = in.readString();
        question = in.readString();

        equipmentType = in.readString();
        serviceType = in.readString();
        Group = in.readString();
        questionType = in.readString();

        isPhoto = in.readString();
        signatureLink = in.readString();
        additionalTextFiled = in.readString();
        name = in.readString();

        code = in.readString();
        photoLink = in.readString();
        optionText = in.readString();
        optionCount = in.readInt();
        equipNumber = in.readString();
        jobTicketNumber = in.readString();

        isAnswered = in.readByte() != 0;
        preAnswer = in.readString();
        dateString = in.readString();
        techName = in.readString();
        customerName = in.readString();
        siteName = in.readString();

        partDescription = in.readString();

        partNumber = in.readString();
        partQuantity = in.readString();
        manPower = in.readString();
        isRepaired = in.readString();
        unitFixed = in.readString();

        surveyStartTime = in.readString();
        geoLat = in.readString();
        geoLang = in.readString();

        submitted = in.readString();


    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;

    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTechName() {
        return techName;
    }

    public void setTechName(String techName) {
        this.techName = techName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getUnitFixed() {
        return unitFixed;
    }

    public void setUnitFixed(String unitFixed) {
        this.unitFixed = unitFixed;
    }

    public String getSubmitted() {
        return submitted;
    }

    public void setSubmitted(String submitted) {
        this.submitted = submitted;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(id);
        dest.writeString(question);

        dest.writeString(equipmentType);
        dest.writeString(serviceType);
        dest.writeString(Group);
        dest.writeString(questionType);
        dest.writeString(isPhoto);
        dest.writeString(signatureLink);

        dest.writeString(additionalTextFiled);
        dest.writeString(name);

        dest.writeString(photoLink);
        dest.writeString(optionText);
        dest.writeInt(optionCount);
        dest.writeString(equipNumber);
        dest.writeString(jobTicketNumber);

        dest.writeString(preAnswer);
        dest.writeString(dateString);
        dest.writeString(unit);
        dest.writeString(techName);
        dest.writeString(customerName);
        dest.writeString(siteName);

        dest.writeString(partDescription);
        dest.writeString(partNumber);
        dest.writeString(partQuantity);
        dest.writeString(manPower);
        dest.writeString(isRepaired);
        dest.writeString(surveyStartTime);
        dest.writeString(geoLang);
        dest.writeString(geoLat);

        dest.writeString(unitFixed);
        dest.writeString(submitted);

        dest.writeByte((byte) (isAnswered ? 1 : 0));


    }

    public static final Creator<DatabasePojo> CREATOR = new Creator<DatabasePojo>() {
        public DatabasePojo createFromParcel(Parcel in) {
            return new DatabasePojo(in);
        }

        public DatabasePojo[] newArray(int size) {
            return new DatabasePojo[size];

        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


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

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getIsPhoto() {
        return isPhoto;
    }

    public void setIsPhoto(String isPhoto) {
        this.isPhoto = isPhoto;
    }

    public String getSignature() {
        return signatureLink;
    }

    public void setSignature(String signature) {
        this.signatureLink = signature;
    }

    public String getAdditionalTextFiled() {
        return additionalTextFiled;
    }

    public void setAdditionalTextFiled(String additionalTextFiled) {
        this.additionalTextFiled = additionalTextFiled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTicketNumber() {
        return jobTicketNumber;
    }

    public void setJobTicketNumber(String jobTicketNumber) {
        this.jobTicketNumber = jobTicketNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getOptionCount() {
        return optionCount;
    }

    public void setOptionCount(int optionCount) {
        this.optionCount = optionCount;
    }

    public String getEquipNumber() {
        return equipNumber;
    }

    public void setEquipNumber(String equipNumber) {
        this.equipNumber = equipNumber;
    }


    public String getOptionText() {
        return optionText;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getSignatureLink() {
        return signatureLink;
    }

    public void setSignatureLink(String signatureLink) {
        this.signatureLink = signatureLink;
    }

    public String getPreAnswer() {
        return preAnswer;
    }

    public void setPreAnswer(String preAnswer) {
        this.preAnswer = preAnswer;
    }

    public void setOptionText(String allOptions) {

        this.optionText = allOptions;

    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }
}
