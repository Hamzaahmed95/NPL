package myApp.hamza.npl;

/**
 * Created by Hamza Ahmed on 11-Aug-17.
 */

public class FullScoringClass2 {

    private String bowler;
    private Float overs;
    private int maiden;
    private int runs;
    private int wicket;
    private Float economy;
    private int z0;
    private int wd;
    private int nb;

    public FullScoringClass2(String bowler, Float overs, int maiden, int runs,int wicket, Float economy, int z0, int wd, int nb) {
        this.bowler = bowler;
        this.overs = overs;
        this.maiden = maiden;
        this.runs = runs;
        this.wicket=wicket;
        this.economy = economy;
        this.z0 = z0;
        this.wd = wd;
        this.nb = nb;
    }

    public int getWicket() {
        return wicket;
    }

    public void setWicket(int wicket) {
        this.wicket = wicket;
    }

    public FullScoringClass2() {
    }

    public String getBowler() {
        return bowler;
    }

    public void setBowler(String Bowler) {
        bowler = Bowler;
    }

    public Float getOvers() {
        return overs;
    }

    public void setOvers(Float overs) {
        this.overs = overs;
    }

    public int getMaiden() {
        return maiden;
    }

    public void setMaiden(int maiden) {
        this.maiden = maiden;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public Float getEconomy() {
        return economy;
    }

    public void setEconomy(Float economy) {
        this.economy = economy;
    }

    public int getZ0() {
        return z0;
    }

    public void setZ0(int z0) {
        this.z0 = z0;
    }

    public int getWd() {
        return wd;
    }

    public void setWd(int wd) {
        this.wd = wd;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }
}
