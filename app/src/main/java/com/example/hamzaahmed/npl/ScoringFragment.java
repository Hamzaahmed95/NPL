package com.example.hamzaahmed.npl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Hamza Ahmed on 13-Jul-17.
 */

public class ScoringFragment extends Fragment {

    private RecyclerView recyclerView;
    private ScoreAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.scorecard,container,false);

        recyclerView = (RecyclerView)view.findViewById(R.id.listOfScoring);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }
    public void updateUI(){
        ScoringLab scoringLab = ScoringLab.get(getActivity());
        List<ScoreCard> score=scoringLab.getBatsmans();
        adapter = new ScoreAdapter(score);
        recyclerView.setAdapter(adapter);

    }

}
