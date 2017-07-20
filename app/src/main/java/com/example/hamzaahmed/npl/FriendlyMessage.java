package com.example.hamzaahmed.npl;

/**
 * Created by Hamza Ahmed on 14-Jul-17.
 */


public class FriendlyMessage {

    private String text;
    private String name;
    private String photoUrl;
    private int color;
    private String date1;

    public FriendlyMessage() {
    }

    public FriendlyMessage(String text, String name, String photoUrl,String date) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
        this.date1=date;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
