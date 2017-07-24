package com.example.hamzaahmed.npl;

/**
 * Created by Hamza Ahmed on 14-Jul-17.
 */

public class Image {
    private String image_title;
    private String image_id;

    public Image(String img_title,String img_id){
        image_title=img_title;
        image_id=img_id;
    }
    public Image(){

    }


    public String getImage_title() {
        return image_title;
    }

    public void setImage_title(String android_version_name) {
        this.image_title = android_version_name;
    }

    public String getImage_ID() {
        return image_id;
    }

    public void setImage_ID(String android_image_url) {
        this.image_id = android_image_url;
    }

}
