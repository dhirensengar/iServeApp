package com.t4t.serviceapp.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by VIKAS SAHU on 21/12/16.
 */

public class SubmitPojo implements Parcelable {
    String id, question, equipmentType, serviceType, Group, signatureLink, answer, name, photoLink, equipNumber, jobTicketNumber, isPhoto, questionType, serviceId,preAnswer,unit,dateString;
    String partDescription,partNumber,partQuantity,manPower,isRepaired,surveyStartTime,geoLat,geoLang;

    public SubmitPojo() {

    }


    public SubmitPojo(Parcel in) {
        id = in.readString();
        question = in.readString();
        equipmentType = in.readString();
        serviceType = in.readString();
        Group = in.readString();
        signatureLink = in.readString();
        answer = in.readString();
        name = in.readString();
        photoLink = in.readString();
        equipNumber = in.readString();
        jobTicketNumber = in.readString();
        isPhoto = in.readString();
        questionType = in.readString();
        preAnswer = in.readString();
        unit = in.readString();
        dateString = in.readString();

        partDescription = in.readString();
        partNumber = in.readString();
        partQuantity = in.readString();
        manPower = in.readString();
        isRepaired = in.readString();

        surveyStartTime = in.readString();
        geoLat = in.readString();
        geoLang = in.readString();

    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
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

    public String getIsRepaired() {
        return isRepaired;
    }

    public void setIsRepaired(String isRepaired) {
        this.isRepaired = isRepaired;
    }

    public String getGeoLang() {
        return geoLang;
    }

    public void setGeoLang(String geoLang) {
        this.geoLang = geoLang;
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

    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(id);
        dest.writeString(question);
        dest.writeString(equipmentType);
        dest.writeString(serviceType);
        dest.writeString(Group);
        dest.writeString(signatureLink);
        dest.writeString(answer);
        dest.writeString(name);
        dest.writeString(photoLink);
        dest.writeString(equipNumber);
        dest.writeString(jobTicketNumber);
        dest.writeString(questionType);

        dest.writeString(isPhoto);
        dest.writeString(preAnswer);

        dest.writeString(unit);
        dest.writeString(dateString);
        dest.writeString(partDescription);
        dest.writeString(partNumber);
        dest.writeString(partQuantity);
        dest.writeString(manPower);
        dest.writeString(isRepaired);

        dest.writeString(surveyStartTime);
        dest.writeString(geoLang);
        dest.writeString(geoLat);
    }

    public static final Creator<SubmitPojo> CREATOR = new Creator<SubmitPojo>() {
        public SubmitPojo createFromParcel(Parcel in) {
            return new SubmitPojo(in);
        }

        public SubmitPojo[] newArray(int size) {
            return new SubmitPojo[size];
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

    public void setGroup(String group) {
        Group = group;
    }

    public String getSignatureLink() {
        return signatureLink;
    }

    public String getPreAnswer() {
        return preAnswer;
    }

    public void setPreAnswer(String preAnswer) {
        this.preAnswer = preAnswer;
    }

    public void setSignatureLink(String signatureLink) {
        this.signatureLink = signatureLink;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getEquipNumber() {
        return equipNumber;
    }

    public void setEquipNumber(String equipNumber) {
        this.equipNumber = equipNumber;
    }

    public String getJobTicketNumber() {
        return jobTicketNumber;
    }

    public void setJobTicketNumber(String jobTicketNumber) {
        this.jobTicketNumber = jobTicketNumber;
    }
}
