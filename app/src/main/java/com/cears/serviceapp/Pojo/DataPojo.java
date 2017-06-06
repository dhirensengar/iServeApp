package com.cears.serviceapp.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by VIKAS SAHU on 21/12/16.
 */

public class DataPojo implements Parcelable, Serializable {
    String id;
    String questionType, isPhoto, code, group, question, answer, isMajor, isMinor, isCleaning, isInspection, isBreakdown;
    String isCentrifugal, isRecipAirCooled, isRecipWaterCooled, isScrewAirCooled, isScrewWaterCooled, isRTU, isSplits, isMiniSplits, isAHU, isFAHU, isFCU, isPump;
    String response1, response2, response3, response4, response5, response6, response7;
    String option1, option2, option3, option4, option5;
    String photoLink, additionalText, optionText;
    String unit, preAnswer, dateString, unitFixed;
    String partDescription, partNumber, partQuantity, manPower, isRepaired;
    int optionCount, checkedPosition;

    boolean isAnswered;

    public DataPojo() {

    }

    public DataPojo(Parcel in) {
        id = in.readString();
        questionType = in.readString();
        isPhoto = in.readString();
        code = in.readString();
        group = in.readString();
        question = in.readString();
        answer = in.readString();
        isMajor = in.readString();
        isMinor = in.readString();
        isCleaning = in.readString();
        isInspection = in.readString();
        isBreakdown = in.readString();
        isCentrifugal = in.readString();
        isRecipAirCooled = in.readString();
        isRecipWaterCooled = in.readString();
        isScrewAirCooled = in.readString();
        isScrewWaterCooled = in.readString();
        isRTU = in.readString();
        isSplits = in.readString();
        isMiniSplits = in.readString();
        isAHU = in.readString();
        isFAHU = in.readString();
        isFCU = in.readString();
        isPump = in.readString();
        response1 = in.readString();
        response2 = in.readString();
        response3 = in.readString();
        response4 = in.readString();
        response5 = in.readString();
        response6 = in.readString();
        response7 = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        option4 = in.readString();
        option5 = in.readString();
        optionCount = in.readInt();
        isAnswered = in.readByte() != 0;
        checkedPosition = in.readInt();
        photoLink = in.readString();
        additionalText = in.readString();
        optionText = in.readString();
        unit = in.readString();
        preAnswer = in.readString();
        dateString = in.readString();
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
        dest.writeString(questionType);
        dest.writeString(isPhoto);
        dest.writeString(code);
        dest.writeString(group);
        dest.writeString(question);
        dest.writeString(answer);
        dest.writeString(isMajor);
        dest.writeString(isMinor);
        dest.writeString(isCleaning);
        dest.writeString(isInspection);
        dest.writeString(isBreakdown);
        dest.writeString(isCentrifugal);
        dest.writeString(isRecipAirCooled);
        dest.writeString(isRecipWaterCooled);
        dest.writeString(isScrewAirCooled);
        dest.writeString(isScrewWaterCooled);
        dest.writeString(isRTU);
        dest.writeString(isSplits);
        dest.writeString(isMiniSplits);
        dest.writeString(isAHU);
        dest.writeString(isFAHU);
        dest.writeString(isFCU);
        dest.writeString(isPump);
        dest.writeString(response1);
        dest.writeString(response2);
        dest.writeString(response3);
        dest.writeString(response4);
        dest.writeString(response5);
        dest.writeString(response6);
        dest.writeString(response7);
        dest.writeString(option1);
        dest.writeString(option2);
        dest.writeString(option3);
        dest.writeString(option4);
        dest.writeString(option5);
        dest.writeInt(optionCount);
        dest.writeByte((byte) (isAnswered ? 1 : 0));
        dest.writeInt(checkedPosition);
        dest.writeString(photoLink);
        dest.writeString(additionalText);
        dest.writeString(optionText);
        dest.writeString(unit);
        dest.writeString(unitFixed);
        dest.writeString(preAnswer);
        dest.writeString(dateString);
        dest.writeString(partDescription);
        dest.writeString(partNumber);
        dest.writeString(partQuantity);
        dest.writeString(manPower);
        dest.writeString(isRepaired);
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public static final Creator<DataPojo> CREATOR = new Creator<DataPojo>() {
        public DataPojo createFromParcel(Parcel in) {
            return new DataPojo(in);
        }

        public DataPojo[] newArray(int size) {
            return new DataPojo[size];

        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setIsPhoto(String isPhoto) {
        this.isPhoto = isPhoto;
    }

    public String getCode() {
        return code;
    }

    public String getUnitFixed() {
        return unitFixed;
    }

    public void setUnitFixed(String unitFixed) {
        this.unitFixed = unitFixed;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getIsMajor() {
        return isMajor;
    }

    public String getPreAnswer() {
        return preAnswer;
    }

    public void setPreAnswer(String preAnswer) {
        this.preAnswer = preAnswer;
    }

    public void setIsMajor(String isMajor) {
        this.isMajor = isMajor;
    }

    public String getIsMinor() {
        return isMinor;
    }

    public void setIsMinor(String isMinor) {
        this.isMinor = isMinor;
    }

    public String getIsCleaning() {
        return isCleaning;
    }

    public void setIsCleaning(String isCleaning) {
        this.isCleaning = isCleaning;
    }

    public String getIsInspection() {
        return isInspection;
    }

    public void setIsInspection(String isInspection) {
        this.isInspection = isInspection;
    }

    public String getIsCentrifugal() {
        return isCentrifugal;
    }

    public void setIsCentrifugal(String isCentrifugal) {
        this.isCentrifugal = isCentrifugal;
    }

    public String getIsRecipAirCooled() {
        return isRecipAirCooled;
    }

    public String getAdditionalText() {
        return additionalText;
    }

    public void setAdditionalText(String additionalText) {
        this.additionalText = additionalText;
    }

    public void setIsRecipAirCooled(String isRecipAirCooled) {
        this.isRecipAirCooled = isRecipAirCooled;
    }

    public String getIsRecipWaterCooled() {
        return isRecipWaterCooled;
    }

    public void setIsRecipWaterCooled(String isRecipWaterCooled) {
        this.isRecipWaterCooled = isRecipWaterCooled;
    }

    public String getIsScrewAirCooled() {
        return isScrewAirCooled;
    }

    public void setIsScrewAirCooled(String isScrewAirCooled) {
        this.isScrewAirCooled = isScrewAirCooled;
    }

    public String getIsScrewWaterCooled() {
        return isScrewWaterCooled;
    }

    public void setIsScrewWaterCooled(String isScrewWaterCooled) {
        this.isScrewWaterCooled = isScrewWaterCooled;
    }

    public String getIsRTU() {
        return isRTU;
    }

    public void setIsRTU(String isRTU) {
        this.isRTU = isRTU;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getIsSplits() {
        return isSplits;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public void setIsSplits(String isSplits) {
        this.isSplits = isSplits;
    }

    public String getIsMiniSplits() {
        return isMiniSplits;
    }

    public void setIsMiniSplits(String isMiniSplits) {
        this.isMiniSplits = isMiniSplits;
    }

    public String getIsAHU() {
        return isAHU;
    }

    public void setIsAHU(String isAHU) {
        this.isAHU = isAHU;
    }

    public String getIsFAHU() {
        return isFAHU;
    }

    public int getOptionCount() {
        return optionCount;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public void setOptionCount(int optionCount) {
        this.optionCount = optionCount;
    }

    public void setIsFAHU(String isFAHU) {
        this.isFAHU = isFAHU;
    }

    public String getIsFCU() {
        return isFCU;
    }

    public void setIsFCU(String isFCU) {
        this.isFCU = isFCU;
    }

    public String getIsPump() {
        return isPump;
    }

    public void setIsPump(String isPump) {
        this.isPump = isPump;
    }

    public String getResponse1() {
        return response1;
    }

    public void setResponse1(String response1) {
        this.response1 = response1;
    }

    public String getResponse2() {
        return response2;
    }

    public void setResponse2(String response2) {
        this.response2 = response2;
    }

    public String getResponse3() {
        return response3;
    }

    public void setResponse3(String response3) {
        this.response3 = response3;
    }

    public String getResponse4() {
        return response4;
    }

    public void setResponse4(String response4) {
        this.response4 = response4;
    }

    public String getResponse5() {
        return response5;
    }

    public void setResponse5(String response5) {
        this.response5 = response5;
    }

    public String getResponse6() {
        return response6;
    }

    public void setResponse6(String response6) {
        this.response6 = response6;
    }

    public String getResponse7() {
        return response7;
    }

    public void setResponse7(String response7) {
        this.response7 = response7;
    }

    public String getIsBreakdown() {
        return isBreakdown;
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

    public void setIsBreakdown(String isBreakdown) {
        this.isBreakdown = isBreakdown;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public int getCheckedPosition() {
        return checkedPosition;
    }

    public void setCheckedPosition(int checkedPosition) {
        this.checkedPosition = checkedPosition;
    }
}
