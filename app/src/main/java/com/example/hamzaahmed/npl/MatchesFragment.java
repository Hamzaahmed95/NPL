package com.example.hamzaahmed.npl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Hamza Ahmed on 15-Jul-17.
 */

public class MatchesFragment extends Fragment {

    private RecyclerView recyclerView;
    private MatchAdapter adapter;
    private ImageView backImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.match,container,false);
        backImageView =(ImageView)view.findViewById(R.id.backButton3);


        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),OptionsActivity.class);
                startActivity(i);
            }
        });
        recyclerView = (RecyclerView)view.findViewById(R.id.listOfMatches);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }
    public void updateUI(){
        MatchLab matchLab = MatchLab.get(getActivity());
        List<Match> match= matchLab.getmMatch();
        adapter = new MatchAdapter(match);
        recyclerView.setAdapter(adapter);

    }

}
