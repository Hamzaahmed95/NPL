package myApp.hamza.npl;

/**
 * Created by Hamza Ahmed on 11-Aug-17.
 */

public class FullScoringClass {
    private String Batsman;
    private String type;
    private int Runs;
    private int B;
    private int F4;
    private int S6;
    private int SS;

    public FullScoringClass(String batsman, String type, int r1, int b, int f4, int s6,int ss) {
        Batsman = batsman;
        this.type = type;
        Runs = r1;
        B = b;
        F4 = f4;
        S6 = s6;
        SS=ss;
    }

    public int getSS() {
        return SS;
    }

    public void setSS(int SS) {
        this.SS = SS;
    }

    public FullScoringClass() {
    }

    public String getBatsman() {
        return Batsman;
    }

    public void setBatsman(String batsman) {
        Batsman = batsman;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRuns() {
        return Runs;
    }

    public void setRuns(int runs) {
        Runs = runs;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }

    public int getF4() {
        return F4;
    }

    public void setF4(int f4) {
        F4 = f4;
    }

    public int getS6() {
        return S6;
    }

    public void setS6(int s6) {
        S6 = s6;
    }
}
