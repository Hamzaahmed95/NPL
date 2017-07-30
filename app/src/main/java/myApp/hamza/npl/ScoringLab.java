package myApp.hamza.npl;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Hamza Ahmed on 13-Jul-17.
 */

public class ScoringLab  {
    private static ScoringLab ScoringLab;
    private final String BATSMAN[] = {
            "Batsman 1",
            "Batsman 2",
            "Batsman 3",
            "Batsman 4",
            "Batsman 5",
            "Batsman 6",
            "Batsman 7",
            "Batsman 8",
            "Batsman 9",
            "Batsman 10",
            "Batsman 11",
    };

    private final String outType[] = {
       "c Bowler 1",
            "lbw",
            " ","c Bowler 1",
            "lbw",
            " ","c Bowler 1",
            "lbw",
            " ","c Bowler 1",
            "lbw",
            " ","c Bowler 1",
            "lbw",
            " ","c Bowler 1",
    };
    private final String outBy[] = {
            "Bowler 1",
            "Bowler 1",
            "Bowler 1",
            "Bowler 1",
            "Bowler 1",
            "Bowler 1",
            "Bowler 1",
            "Bowler 1",
            "Bowler 1",
            "Bowler 1",
            "Bowler 1",

    };
    private final String Runs[] = {
           "35",
            "35",
            "35",
            "35",
            "35",
            "35",
            "35",
            "35",
            "35",
            "35",
            "35",
    };
    private final String balls[] = {
            "(1)",
            "(1)",
            "(1)",
            "(1)",
            "(1)",
            "(1)",
            "(1)",
            "(1)",
            "(1)",
            "(1)",
            "(1)",
    };

    private List<ScoreCard> mScoreCard;

    public static ScoringLab get(Context context){
        if(ScoringLab == null){
            ScoringLab=new ScoringLab(context);
        }
        return ScoringLab;
    }
    private ScoringLab(Context context){
        mScoreCard = new ArrayList<>();
        for(int i = 0; i< Runs.length; i++){
            ScoreCard ScoreCard = new ScoreCard();
            ScoreCard.setBatsman(BATSMAN[i]);
            ScoreCard.setOutType(outType[i]);
            ScoreCard.setOutBy(outBy[i]);
            ScoreCard.setRuns(Runs[i]);
            ScoreCard.setBalls(balls[i]);
            mScoreCard.add(ScoreCard);
        }
    }

    public List<ScoreCard> getBatsmans(){
        return mScoreCard;
    }

    public ScoreCard getBatsman(UUID id){
        for(ScoreCard ScoreCard:mScoreCard){
            if(ScoreCard.getId().equals(id)){
                return ScoreCard;
            }
        }
        return null;
    }
}
