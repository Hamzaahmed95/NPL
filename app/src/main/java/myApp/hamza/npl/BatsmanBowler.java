package myApp.hamza.npl;

/**
 * Created by Hamza Ahmed on 25-Jul-17.
 */

public class BatsmanBowler {

    private String Batsman1;
    private String Batsman2;
    private String Bowler1;
    private String Bowler2;
    private String url1;
    private String url2;

    public BatsmanBowler(String batsman1,String batsman2,String bowler1,String bowler2,String URL1,String URL2){
        Batsman1=batsman1;
        Batsman2=batsman2;
        Bowler1=bowler1;
        Bowler2=bowler2;
        url1=URL1;
        url2=URL2;

    }



    public BatsmanBowler(){

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

    public String getBatsman1() {
        return Batsman1;
    }

    public void setBatsman1(String batsman1) {
        Batsman1 = batsman1;
    }

    public String getBatsman2() {
        return Batsman2;
    }

    public void setBatsman2(String batsman2) {
        Batsman2 = batsman2;
    }

    public String getBowler1() {
        return Bowler1;
    }

    public void setBowler1(String bowler1) {
        Bowler1 = bowler1;
    }

    public String getBowler2() {
        return Bowler2;
    }

    public void setBowler2(String bowler2) {
        Bowler2 = bowler2;
    }
}
