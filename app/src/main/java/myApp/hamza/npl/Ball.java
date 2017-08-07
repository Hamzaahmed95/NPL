package myApp.hamza.npl;

/**
 * Created by Hamza Ahmed on 07-Aug-17.
 */

public class Ball {

    private int ball;
    private int turn;

    public Ball(int ball,int turn) {
        this.ball = ball;
        this.turn = turn;
    }
    public Ball() {
     }

    public int getBall() {
        return ball;
    }

    public int getTurn() {
        return turn;
    }
}
