package com.example.hamzaahmed.npl;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Hamza Ahmed on 15-Jul-17.
 */

public class MatchLab {
    private static MatchLab MatchLab;
    private final Integer Team1[] = {
         R.drawable.p1,
            R.drawable.p2,
            R.drawable.p1,
            R.drawable.p4,
            R.drawable.p6,
            R.drawable.p4,
            R.drawable.p2,
            R.drawable.p3,
            R.drawable.p4,
            R.drawable.p5,
    };
    private final Integer Team2[] = {
            R.drawable.p3,
            R.drawable.p3,
            R.drawable.p2,
            R.drawable.p5,
            R.drawable.p3,
            R.drawable.p1,
            R.drawable.p4,
            R.drawable.p5,
            R.drawable.p2,
            R.drawable.p1,
    };

    private final String MatchDate[] = {
            "Today",
            "Today",
            "Today",
            "Today",
            "Today",
            "Today",
            "Today",
            "Today",
            "Today",
            "Today",
    };
    private final String MatchResult[] = {
            "Team1 won by 10 wickets",
            "Team1 won by 10 wickets",
            "Team1 won by 10 wickets",
            "Team1 won by 10 wickets",
            "Team1 won by 10 wickets",
            "Team1 won by 10 wickets",
            "Team1 won by 10 wickets",
            "Team1 won by 10 wickets",
            "Team1 won by 10 wickets",
            "Team1 won by 10 wickets",
    };
    private final String MatchNo[] = {
            "4th Match",
            "5th Match",
            "6th Match",
            "7th Match",
            "8th Match",
            "9th Match",
            "10th Match",
            "11th Match",
            "12th Match",
            "13th Match",
    };
    
    private List<Match> mMatch;

    public static MatchLab get(Context context){
        if(MatchLab == null){
            MatchLab =new MatchLab(context);
        }
        return MatchLab;
    }
    private MatchLab(Context context){
        mMatch = new ArrayList<>();
        for(int i = 0; i< MatchNo.length; i++){
           Match match = new Match();
            match.setImage1Id(Team1[i]);
            match.setImage2Id(Team2[i]);
            match.setMatchDate(MatchDate[i]);
            match.setMatchNo(MatchNo[i]);
            match.setMatchResult(MatchResult[i]);
            
            mMatch.add(match);
        }
    }

    public List<Match> getmMatch(){
        return mMatch;
    }

    public Match getmMatch(UUID id){
        for(Match Match:mMatch){
            if(Match.getId().equals(id)){
                return Match;
            }
        }
        return null;
    }
}
