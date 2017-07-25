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
    private Integer Runs1;
    private Integer Runs2;

    private Integer ball1;
    private Integer ball2;

    public LiveScore(){

    }

    public LiveScore(Integer runs,Integer wicket,float overs, Integer ball,Integer team,Integer balls,Integer ballNo,
                     Integer runs1,Integer runs2,Integer Balls1,Integer Balls2
                     ){
        Runs=runs;
        Wicket=wicket;
        Overs=overs;
        Ball=ball;
        Team=team;
        Balls=balls;
        BallNo=ballNo;
        Runs1=runs1;
        Runs2=runs2;
        ball1=Balls1;
        ball2=Balls2;

    }

    public Integer getRuns1() {
        return Runs1;
    }

    public void setRuns1(Integer runs1) {
        Runs1 = runs1;
    }

    public Integer getRuns2() {
        return Runs2;
    }

    public void setRuns2(Integer runs2) {
        Runs2 = runs2;
    }


    public Integer getBall1() {
        return ball1;
    }

    public void setBall1(Integer ball1) {
        this.ball1 = ball1;
    }

    public Integer getBall2() {
        return ball2;
    }

    public void setBall2(Integer ball2) {
        this.ball2 = ball2;
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
