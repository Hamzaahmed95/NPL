package com.example.hamzaahmed.npl;

/**
 * Created by Hamza Ahmed on 14-Jul-17.
 */


public class FriendlyHashtag {

    private String text;
    private String name;
    private String photoUrl;
    private int color;

    public FriendlyHashtag() {
    }

    public FriendlyHashtag(String text, String name, String photoUrl) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
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
