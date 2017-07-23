package com.example.hamzaahmed.npl;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

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
    ProgressBar mprogressBar;
    ProgressBar mprogressBar1;
    ProgressBar mprogressBar2;
    ProgressBar mprogressBar3;
    ProgressBar mprogressBar4;
    ProgressBar mprogressBar5;
    private ImageView backButton6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teams);
        backButton6=(ImageView)findViewById(R.id.backButton6);

        backButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Teams.this,OptionsActivity.class);
                startActivity(i);
            }
        });
        team1 = (ImageView)findViewById(R.id.team1);
        team2 = (ImageView)findViewById(R.id.team2);
        team3 = (ImageView)findViewById(R.id.team3);
        team4 = (ImageView)findViewById(R.id.team4);
        team5 = (ImageView)findViewById(R.id.team5);
        team6 = (ImageView)findViewById(R.id.team6);
        mprogressBar = (ProgressBar) findViewById(R.id.progressBarTeam);
        ObjectAnimator anim = ObjectAnimator.ofInt(mprogressBar, "progress", 0, 100);
        anim.setDuration(4000);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();
        mprogressBar1 = (ProgressBar) findViewById(R.id.progressBarTeam1);
        ObjectAnimator anim1 = ObjectAnimator.ofInt(mprogressBar1, "progress", 0, 100);
        anim1.setDuration(4000);
        anim1.setInterpolator(new DecelerateInterpolator());
        anim1.start();
        mprogressBar2 = (ProgressBar) findViewById(R.id.progressBarTeam2);
        ObjectAnimator anim2 = ObjectAnimator.ofInt(mprogressBar2, "progress", 0, 100);
        anim2.setDuration(4000);
        anim2.setInterpolator(new DecelerateInterpolator());
        anim2.start();

        mprogressBar3 = (ProgressBar) findViewById(R.id.progressBarTeam3);
        ObjectAnimator anim3 = ObjectAnimator.ofInt(mprogressBar3, "progress", 0, 100);
        anim3.setDuration(4000);
        anim3.setInterpolator(new DecelerateInterpolator());
        anim3.start();
        mprogressBar4 = (ProgressBar) findViewById(R.id.progressBarTeam4);
        ObjectAnimator anim4 = ObjectAnimator.ofInt(mprogressBar4, "progress", 0, 100);
        anim4.setDuration(4000);
        anim4.setInterpolator(new DecelerateInterpolator());
        anim4.start();
        mprogressBar5 = (ProgressBar) findViewById(R.id.progressBarTeam5);
        ObjectAnimator anim5 = ObjectAnimator.ofInt(mprogressBar5, "progress", 0, 100);
        anim5.setDuration(4000);
        anim5.setInterpolator(new DecelerateInterpolator());
        anim5.start();

        nawaitUnited ="https://firebasestorage.googleapis.com/v0/b/npl2017-2bca3.appspot.com/o/point_table%2Fimage%3A7155?alt=media&token=3d0a6582-9735-48b8-aef9-ef1a89b82e58";
        ShanENawait = "https://firebasestorage.googleapis.com/v0/b/npl2017-2bca3.appspot.com/o/point_table%2Fimage%3A7153?alt=media&token=9bd6b919-e305-40b6-96ca-5e719624932f";
        NawaitJanbaz="https://firebasestorage.googleapis.com/v0/b/npl2017-2bca3.appspot.com/o/point_table%2Fimage%3A7150?alt=media&token=5ebdaf59-a262-42c8-94f4-9b268dd4662e";
        NawaitRoyals="https://firebasestorage.googleapis.com/v0/b/npl2017-2bca3.appspot.com/o/point_table%2F7200?alt=media&token=99f626cf-fa35-4799-94f8-3496eb17b446";
        NawaitAces="https://firebasestorage.googleapis.com/v0/b/npl2017-2bca3.appspot.com/o/point_table%2Fimage%3A7154?alt=media&token=ace7ae39-22f7-4d48-ac3f-e2de579e214b";
        NawaitSultan="https://firebasestorage.googleapis.com/v0/b/npl2017-2bca3.appspot.com/o/point_table%2Fimage%3A7178?alt=media&token=00f64b84-61f1-4fee-a465-7ba16b4300eb";



        Handler handler = new Handler();
        if (isFirstTime()) {



            handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                loadImages();

            }
        }, 3000);}
        else{
            loadImages();
        }

        team1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });


    }
    private boolean isFirstTime()
    {
        SharedPreferences preferences = this.getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }
    private void loadImages(){if(NawaitSultan!=null){
        Glide.with(team6.getContext())
                .load(NawaitSultan)
                .into(team6);

        mprogressBar5.setVisibility(View.INVISIBLE);
    }

        if(nawaitUnited!=null){
            Glide.with(team1.getContext())
                    .load(nawaitUnited)
                    .into(team1);
            mprogressBar.setVisibility(View.INVISIBLE);
        }
        if(ShanENawait!=null) {
            Glide.with(team2.getContext())
                    .load(ShanENawait)
                    .into(team2);

            mprogressBar1.setVisibility(View.INVISIBLE);
        }
        if(NawaitJanbaz!=null) {
            Glide.with(team3.getContext())
                    .load(NawaitJanbaz)
                    .into(team3);
            mprogressBar2.setVisibility(View.INVISIBLE);
        }

        if(NawaitRoyals!=null) {
            Glide.with(team4.getContext())
                    .load(NawaitRoyals)
                    .into(team4);

            mprogressBar3.setVisibility(View.INVISIBLE);
        }
        if(NawaitAces!=null) {
            Glide.with(team5.getContext())
                    .load(NawaitAces)
                    .into(team5);

            mprogressBar4.setVisibility(View.INVISIBLE);
        }

    }
    private void showDialog() {
        // custom dialog
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.text);

        // set the custom dialog components - text, image and button

        // Close Button

        // Buy Button

        ImageView v1 =(ImageView)dialog.findViewById(R.id.janbaz);

        String url ="https://firebasestorage.googleapis.com/v0/b/npl2017-2bca3.appspot.com/o/point_table%2F7168?alt=media&token=d41424b6-c90c-4156-87f1-f2bac4a6e9e7";
        if(url!=null) {
            Glide.with(v1.getContext())
                    .load(url)
                    .into(v1);
        }




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