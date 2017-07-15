package com.example.hamzaahmed.npl;

/**
 * Created by Hamza Ahmed on 15-Jul-17.
 */

public class LiveScore {
    private Integer Runs;
    private Integer Wicket;
    private float Overs;
    private Integer Ball;
    private Integer Team;
    private Integer Balls;
    private Integer BallNo;
    public LiveScore(){

    }

    public LiveScore(Integer runs,Integer wicket,float overs, Integer ball,Integer team,Integer balls,Integer ballNo){
        Runs=runs;
        Wicket=wicket;
        Overs=overs;
        Ball=ball;
        Team=team;
        Balls=balls;
        BallNo=ballNo;



    }

    public Integer getBalls() {
        return Balls;
    }

    public void setBalls(Integer balls) {
        Balls = balls;
    }

    public Integer getBallNo() {
        return BallNo;
    }

    public void setBallNo(Integer ballNo) {
        BallNo = ballNo;
    }

    public Integer getTeam() {
        return Team;
    }

    public void setTeam(Integer team) {
        this.Team = team;
    }

    public Integer getRuns() {
        return Runs;
    }

    public void setRuns(Integer runs) {
        Runs = runs;
    }

    public Integer getWicket() {
        return Wicket;
    }

    public void setWicket(Integer wicket) {
        Wicket = wicket;
    }

    public float getOvers() {
        return Overs;
    }

    public void setOvers(float overs) {
        Overs = overs;
    }

    public Integer getBall() {
        return Ball;
    }

    public void setBall(Integer ball) {
        Ball = ball;
    }

}
