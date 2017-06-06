package com.cears.serviceapp.models;

import java.io.Serializable;


public class PreQuestionModel implements Serializable {

    String preQid;
    String preQuestion;
    String answer;



    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPreQid() {
        return preQid;
    }

    public void setPreQid(String preQid) {
        this.preQid = preQid;
    }

    public String getPreQuestion() {
        return preQuestion;
    }

    public void setPreQuestion(String preQuestion) {
        this.preQuestion = preQuestion;
    }
}
