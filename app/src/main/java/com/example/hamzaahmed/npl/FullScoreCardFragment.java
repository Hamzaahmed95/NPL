package com.example.hamzaahmed.npl;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Hamza Ahmed on 22-Jul-17.
 */

public class FullScoreCardFragment extends Fragment {
    private RecyclerView recyclerView;
    private ScoreAdapter adapter;
    private ImageView Button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.scoring, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.listOfScoring);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        Button =(ImageView) view.findViewById(R.id.backButton2);

        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),ScoringActivity.class);
                startActivity(i);
            }
        });

        return view;

    }

    public void updateUI(){
        ScoringLab scoringLab = ScoringLab.get(getActivity());
        List<ScoreCard> score=scoringLab.getBatsmans();
        adapter = new ScoreAdapter(score);
        recyclerView.setAdapter(adapter);
    }

    }
