package com.example.hamzaahmed.npl;

/**
 * Created by Hamza Ahmed on 18-Jul-17.
 */

public class pointTablePicture {

    private String photoUrl;
    private String updatedDate;

    public pointTablePicture(){

    }

    public pointTablePicture(String url,String date){
        photoUrl=url;
        updatedDate=date;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
