package com.example.hamzaahmed.npl;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.facebook.share.widget.SendButton;

/**
 * Created by Hamza Ahmed on 19-Jul-17.
 */

public class UsernameAndPasswordFragment extends Fragment {

    EditText FavPlayer;
    EditText Username;
    Spinner BestTeam;;

    Button SendButtonQuestion;
    Spinner BestPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.username_password,container,false);

        FavPlayer = (EditText)view.findViewById(R.id.FavPlayer);
        Username=(EditText)view.findViewById(R.id.Username);
        BestTeam = (Spinner) view.findViewById(R.id.favTeam);
        BestPlayer = (Spinner) view.findViewById(R.id.FavPlayer2);

        SendButtonQuestion = (Button) view.findViewById(R.id.sendButtonQuesion);
        String[] items = new String[]{"Nawait United", "Nawait Aces", "Nawait Royals", "Shan-e-Nawait", "Nawait Sultan", "Nawait Janbaz"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        Log.d("quantity",""+adapter);

        BestTeam.setAdapter(adapter);
        BestTeam.setPrompt("select");
        String[] items2 = new String[]{"select","Saqib", "Ali", "Khateeb Mairaj","others"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items2);
        Log.d("quantity",""+adapter2);

        BestPlayer.setAdapter(adapter2);
        BestPlayer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                FavPlayer.setVisibility(View.INVISIBLE);
                SendButtonQuestion.setEnabled(true);

                switch (position) {

                    case 0:
                        // set editbox visible
                        Log.d("case : "," "+position);

                        SendButtonQuestion.setEnabled(false);
                        SendButtonQuestion.setTextColor(Color.RED);
                        break;
                    case 1:
                        // set editbox invivible
                        Log.d("case : "," "+position);

                        SendButtonQuestion.setTextColor(Color.BLACK);
                        SendButtonQuestion.setEnabled(true);
                        break;
                    case 2:
                        // set editbox invivible
                        Log.d("case : "," "+position);
                        SendButtonQuestion.setTextColor(Color.BLACK);
                        SendButtonQuestion.setEnabled(true);
                        break;
                    case 3:
                        Log.d("case : "," "+position);
                        SendButtonQuestion.setTextColor(Color.BLACK);
                        SendButtonQuestion.setEnabled(true);
                        break;
                    case 4:
                        SendButtonQuestion.setTextColor(Color.BLACK);
                        FavPlayer.setVisibility(View.VISIBLE);
                        SendButtonQuestion.setEnabled(true);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // set editbox invivible
                FavPlayer.setVisibility(View.VISIBLE);

            }
        });


        SendButtonQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),OptionsActivity.class);
                startActivity(i);

            }
        });

        return view;
    }
}
