package com.example.hamzaahmed.npl;

/**
 * Created by Hamza Ahmed on 29-Jul-17.
 */

public class Poll {
    String questionSet;
    String answer1;
    String answer2;
    String url1;
    String url2;
    String url3;
    String url4;
    String mUsername;
    public Poll(String username,String quesSet, String a1, String a2, String Url1, String Url2, String Url3, String Url4){

        mUsername=username;
        answer1=a1;
        answer2=a2;
        url1=Url1;
        url2=Url2;
        url3=Url3;
        url4=Url4;
        questionSet=quesSet;
    }

    public String getQuestionSet() {
        return questionSet;
    }

    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public void setQuestionSet(String questionSet) {
        this.questionSet = questionSet;
    }

    public Poll(){

    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getUrl3() {
        return url3;
    }

    public void setUrl3(String url3) {
        this.url3 = url3;
    }

    public String getUrl4() {
        return url4;
    }

    public void setUrl4(String url4) {
        this.url4 = url4;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }
}
