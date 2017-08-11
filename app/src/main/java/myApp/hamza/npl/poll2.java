package myApp.hamza.npl;

/**
 * Created by Hamza Ahmed on 18-Jul-17.
 */

public class Poll2 {

    private String photoUrl;
    private String updatedDate;
    private String mUsername;

    public Poll2(){

    }

    public Poll2(String url, String date, String username){
        photoUrl=url;
        updatedDate=date;
        mUsername=username;
    }

    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
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
