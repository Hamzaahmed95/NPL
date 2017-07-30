package myApp.hamza.npl;

import java.util.UUID;

/**
 * Created by Hamza Ahmed on 13-Jul-17.
 */

public class ScoreCard {

    private String batsman;
    private UUID Id;
    private String bowler;
    private String outType;
    private String outBy;
    private String runs;
    private String balls;

    public String getBatsman() {
        return batsman;
    }

    public void setBatsman(String batsman) {
        this.batsman = batsman;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getBowler() {
        return bowler;
    }

    public void setBowler(String bowler) {
        this.bowler = bowler;
    }

    public String getOutType() {
        return outType;
    }

    public void setOutType(String outType) {
        this.outType = outType;
    }

    public String getOutBy() {
        return outBy;
    }

    public void setOutBy(String outBy) {
        this.outBy = outBy;
    }

    public String getRuns() {
        return runs;
    }

    public void setRuns(String runs) {
        this.runs = runs;
    }

    public String getBalls() {
        return balls;
    }

    public void setBalls(String balls) {
        this.balls = balls;
    }
}
