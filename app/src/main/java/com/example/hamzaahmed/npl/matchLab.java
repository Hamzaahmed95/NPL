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
    private int count=0;
    private String[] array1;
    private final Integer Team1[] = {
            R.drawable.team4,
            R.drawable.team7,
            R.drawable.team7,
            R.drawable.team3,
            R.drawable.team7,
            R.drawable.team6,
            R.drawable.team6,
            R.drawable.team2,
            R.drawable.team6,
            R.drawable.team4,
            R.drawable.team5,
            R.drawable.team2,
            R.drawable.team3,
            R.drawable.team2,
            R.drawable.team5,
    };
    private final Integer Team2[] = {
            R.drawable.team5,
            R.drawable.team6,
            R.drawable.team5,
            R.drawable.team4,
            R.drawable.team3,
            R.drawable.team2,
            R.drawable.team4,
            R.drawable.team5,
            R.drawable.team3,
            R.drawable.team7,
            R.drawable.team3,
            R.drawable.team4,
            R.drawable.team2,
            R.drawable.team7,
            R.drawable.team6,
    };

    private final String MatchDate[] = {
            "2nd July,2017",
            "2nd July,2017",
            "9th July 2017",
            "9th July 2017",
            "16th July 2017",
            "16th July 2017",
            "23th July 2017",
            "23th July 2017",
            "30th July 2017",
            "30th July 2017",
            "6th Aug 2017",
            "6th Aug 2017",
            "15th Aug 2017",
            "20th Aug 2017",
            "20th Aug 2017",
    };
    private final String MatchResult[] = {
            "Nawait Janbaz won by 65 runs",
            "Nawait Royals won by 45 runs",
            "Nawait Janbaz won by 18 runs",
            "Shan e Nawait won by 4 wickets",
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
            "Team1 won by 10 wickets",
    };
    private final String MatchNo[] = {
            "1st Match",
            "2nd Match",
            "3rd Match",
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
            "14th Match",
            "15th Match",
    };
    private final String MatchVenue[] = {
            "RLCA",
            "RLCA",
            "ANU BHAI PARK",
            "ANU BHAI PARK",
            "EASTERN STAR",
            "EASTERN STAR",
            "AL MANSOORA",
            "AL MANSOORA",
            "RLCA",
            "RLCA",
            "ANU BHAI PARK",
            "ANU BHAI PARK",
            "EIDGAH GROUND",
            "EIDGAH GROUND",
            "EIDGAH GROUND",
    };
    
    private List<Match> mMatch;

    public static MatchLab get(Context context){
        if(MatchLab == null){
            MatchLab =new MatchLab(context);
        }
        return MatchLab;
    }
    public MatchLab(Context context,String result){
        array1 = result.split("-");
        mMatch = new ArrayList<>();
        for(int i=0;i<array1.length;i++){
            System.out.println(i+" "+array1[i]);
        }
       // System.out.println(result);
  //      System.out.println(match2.getMatchResult());
        for(int i = 0; i< MatchNo.length; i++){
            System.out.println(array1[i]);
            Match match = new Match();
            match.setImage1Id(Team1[i]);
            match.setImage2Id(Team2[i]);
            match.setMatchDate(MatchDate[i]);
            match.setMatchNo(MatchNo[i]);
            match.setVenue(MatchVenue[i]);
           match.setMatchResult(array1[i]);
            mMatch.add(match);
        }
    }
    private MatchLab(Context context){
        mMatch = new ArrayList<>();
        for(int i = 0; i< MatchNo.length; i++){
           Match match = new Match();
            match.setImage1Id(Team1[i]);
            match.setImage2Id(Team2[i]);
            match.setMatchDate(MatchDate[i]);
            match.setMatchNo(MatchNo[i]);
            match.setVenue(MatchVenue[i]);
            match.setMatchResult(MatchResult[i]);
            mMatch.add(match);
        }
    }

    public List<Match> getmMatch(){
        return mMatch;
    }

}
