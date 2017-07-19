package com.example.hamzaahmed.npl;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Hamza Ahmed on 14-Jul-17.
 */

public class Teams extends Activity {

    private ImageView team1;
    private ImageView team2;
    private ImageView team3;
    private ImageView team4;
    private ImageView team5;
    private ImageView team6;
    private String nawaitUnited;
    private String ShanENawait;
    private String NawaitJanbaz;
    private String NawaitRoyals;
    private String NawaitAces;
    private String NawaitSultan;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teams);
        team1 = (ImageView)findViewById(R.id.team1);
        team2 = (ImageView)findViewById(R.id.team2);
        team3 = (ImageView)findViewById(R.id.team3);
        team4 = (ImageView)findViewById(R.id.team4);
        team5 = (ImageView)findViewById(R.id.team5);
        team6 = (ImageView)findViewById(R.id.team6);
        nawaitUnited ="https://firebasestorage.googleapis.com/v0/b/npl2017-2bca3.appspot.com/o/point_table%2Fimage%3A7155?alt=media&token=3d0a6582-9735-48b8-aef9-ef1a89b82e58";
        ShanENawait = "https://firebasestorage.googleapis.com/v0/b/npl2017-2bca3.appspot.com/o/point_table%2Fimage%3A7153?alt=media&token=9bd6b919-e305-40b6-96ca-5e719624932f";
        NawaitJanbaz="https://firebasestorage.googleapis.com/v0/b/npl2017-2bca3.appspot.com/o/point_table%2Fimage%3A7150?alt=media&token=5ebdaf59-a262-42c8-94f4-9b268dd4662e";
        NawaitRoyals="https://firebasestorage.googleapis.com/v0/b/npl2017-2bca3.appspot.com/o/point_table%2F7161?alt=media&token=3f890c8d-a917-4110-a91d-f4504f94d492";
        NawaitAces="https://firebasestorage.googleapis.com/v0/b/npl2017-2bca3.appspot.com/o/point_table%2Fimage%3A7154?alt=media&token=ace7ae39-22f7-4d48-ac3f-e2de579e214b";
        NawaitSultan="https://firebasestorage.googleapis.com/v0/b/npl2017-2bca3.appspot.com/o/point_table%2Fimage%3A7178?alt=media&token=00f64b84-61f1-4fee-a465-7ba16b4300eb";

        if(NawaitSultan!=null){
            Glide.with(team6.getContext())
                    .load(NawaitSultan)
                    .into(team6);
        }

        if(nawaitUnited!=null){
        Glide.with(team1.getContext())
                .load(nawaitUnited)
                .into(team1);
        }
        if(ShanENawait!=null) {
            Glide.with(team2.getContext())
                    .load(ShanENawait)
                    .into(team2);
        }
        if(NawaitJanbaz!=null) {
            Glide.with(team3.getContext())
                    .load(NawaitJanbaz)
                    .into(team3);
        }

        if(NawaitRoyals!=null) {
            Glide.with(team4.getContext())
                    .load(NawaitRoyals)
                    .into(team4);
        }
        if(NawaitAces!=null) {
            Glide.with(team5.getContext())
                    .load(NawaitAces)
                    .into(team5);
        }



        team1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });


    }
    private void showDialog() {
        // custom dialog
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.text);

        // set the custom dialog components - text, image and button

        // Close Button

        // Buy Button
        ImageButton Close = (ImageButton) dialog.findViewById(R.id.close1);
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}