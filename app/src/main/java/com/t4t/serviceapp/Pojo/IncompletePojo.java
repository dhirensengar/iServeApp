package com.t4t.serviceapp.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by VIKAS SAHU on 30/12/16.
 */

public class IncompletePojo implements Parcelable {
    String id, question, equipmentType, serviceType, Group, isPhoto, questionType, signatureLink, additionalTextFiled, name, code, optionText, photoLink, equipNumber, jobTicketNumber, answer;
    String option1, option2, option3, option4, option5, preAnswer, unit, unitFixed;
    int optionCount, checkedPosition;
    String partDescription, partNumber, partQuantity, manPower, isRepaired;
    String dateString;
    boolean isAnswered;

    public IncompletePojo() {

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

    public IncompletePojo(Parcel in) {
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
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        option4 = in.readString();
        option5 = in.readString();
        answer = in.readString();
        checkedPosition = in.readInt();
        preAnswer = in.readString();

        dateString = in.readString();
        unit = in.readString();
        unitFixed = in.readString();

        partDescription = in.readString();
        partNumber = in.readString();
        partQuantity = in.readString();
        manPower = in.readString();
        isRepaired = in.readString();


    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
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
        dest.writeByte((byte) (isAnswered ? 1 : 0));
        dest.writeString(option1);
        dest.writeString(option2);
        dest.writeString(option3);
        dest.writeString(option4);
        dest.writeString(option5);
        dest.writeString(answer);
        dest.writeInt(checkedPosition);
        dest.writeString(preAnswer);
        dest.writeString(dateString);
        dest.writeString(unit);
        dest.writeString(unitFixed);
        dest.writeString(partDescription);
        dest.writeString(partNumber);
        dest.writeString(partQuantity);
        dest.writeString(manPower);
        dest.writeString(isRepaired);
    }

    public static final Creator<IncompletePojo> CREATOR = new Creator<IncompletePojo>() {
        public IncompletePojo createFromParcel(Parcel in) {
            return new IncompletePojo(in);
        }

        public IncompletePojo[] newArray(int size) {
            return new IncompletePojo[size];

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

    public String getIsPhoto() {
        return isPhoto;
    }

    public void setIsPhoto(String isPhoto) {
        this.isPhoto = isPhoto;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getSignatureLink() {
        return signatureLink;
    }

    public void setSignatureLink(String signatureLink) {
        this.signatureLink = signatureLink;
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

    public String getPreAnswer() {
        return preAnswer;
    }

    public void setPreAnswer(String preAnswer) {
        this.preAnswer = preAnswer;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getUnitFixed() {
        return unitFixed;
    }

    public void setUnitFixed(String unitFixed) {
        this.unitFixed = unitFixed;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getOption5() {
        return option5;
    }

    public void setOption5(String option5) {
        this.option5 = option5;
    }

    public int getOptionCount() {
        return optionCount;
    }

    public void setOptionCount(int optionCount) {
        this.optionCount = optionCount;
    }

    public int getCheckedPosition() {
        return checkedPosition;
    }

    public void setCheckedPosition(int checkedPosition) {
        this.checkedPosition = checkedPosition;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
}
