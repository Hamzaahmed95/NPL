package myApp.hamza.npl;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

    ProgressBar mprogressBar;
    ProgressBar mprogressBar1;
    ProgressBar mprogressBar2;
    ProgressBar mprogressBar3;
    ProgressBar mprogressBar4;
    ProgressBar mprogressBar5;
    Button Team;
    Button Team1;
    Button Team2;
    Button Team3;
    Button Team4;
    Button Team5;
    Button Team6;
    Button Team7;
    Button Team8;
    Button Team9;
    Button Team10;
    Button Team11;
    private ImageView backButton6;
    String name1;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teams);
        backButton6=(ImageView)findViewById(R.id.backButton6);
        mFirebaseAuth = FirebaseAuth.getInstance();

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
        Team = (Button)findViewById(R.id.ButtonTeam);
        Team1 = (Button)findViewById(R.id.ButtonTeam1);
        Team2 = (Button)findViewById(R.id.ButtonTeam2);
        Team3 = (Button)findViewById(R.id.ButtonTeam3);
        Team4 = (Button)findViewById(R.id.ButtonTeam4);
        Team5 = (Button)findViewById(R.id.ButtonTeam5);
        Team6 = (Button)findViewById(R.id.ButtonTeam6);
        Team7 = (Button)findViewById(R.id.ButtonTeam7);
        Team8 = (Button)findViewById(R.id.ButtonTeam8);
        Team9 = (Button)findViewById(R.id.ButtonTeam9);
        Team10 = (Button)findViewById(R.id.ButtonTeam10);
        Team11 = (Button)findViewById(R.id.ButtonTeam11);


        Team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Teams.this,NawaitUnitedActivity.class);
                startActivity(i);
            }
        });
        Team1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Teams.this,ShaneNawaitActivity.class);
                startActivity(i);
            }
        });
        Team2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Teams.this,NawaitJanbazActivity.class);
                startActivity(i);
            }
        });
        Team3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Teams.this,NawaitRoyalsActivity.class);
                startActivity(i);
            }
        });
        Team4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Teams.this,NawaitAcesActivity.class);
                startActivity(i);
            }
        });
        Team5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Teams.this,NawaitSultanActivity.class);
                startActivity(i);
            }
        });

        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user!=null){
                    //user is signed in
                    name1 =user.getDisplayName();
                    Team9.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(Teams.this,RoyalStatsActivity.class);
                            i.putExtra("username",name1);
                            startActivity(i);
                        }
                    });
                    Team6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(Teams.this,UnitedStatsActivity.class);
                            i.putExtra("username",name1);
                            startActivity(i);
                        }
                    });
                    Team7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(Teams.this,ShanStatsActivity.class);
                            i.putExtra("username",name1);
                            startActivity(i);
                        }
                    });
                    Team8.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(Teams.this,JanbazStatsActivity.class);
                            i.putExtra("username",name1);
                            startActivity(i);
                        }
                    });
                    ;
                    Team10.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(Teams.this,AcesStatsActivity.class);
                            i.putExtra("username",name1);
                            startActivity(i);
                        }
                    });
                    Team11.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(Teams.this,SultanStatsActivity.class);
                            i.putExtra("username",name1);
                            startActivity(i);
                        }
                    });


                }
                else{
                    //user is signed out



                }
            };
        };


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

//        mprogressBar5.setVisibility(View.INVISIBLE);
    }

        if(nawaitUnited!=null){
            Glide.with(team1.getContext())
                    .load(nawaitUnited)
                    .into(team1);
  //          mprogressBar.setVisibility(View.INVISIBLE);
        }
        if(ShanENawait!=null) {
            Glide.with(team2.getContext())
                    .load(ShanENawait)
                    .into(team2);

    //        mprogressBar1.setVisibility(View.INVISIBLE);
        }
        if(NawaitJanbaz!=null) {
            Glide.with(team3.getContext())
                    .load(NawaitJanbaz)
                    .into(team3);
      //      mprogressBar2.setVisibility(View.INVISIBLE);
        }

        if(NawaitRoyals!=null) {
            Glide.with(team4.getContext())
                    .load(NawaitRoyals)
                    .into(team4);

        //    mprogressBar3.setVisibility(View.INVISIBLE);
        }
        if(NawaitAces!=null) {
            Glide.with(team5.getContext())
                    .load(NawaitAces)
                    .into(team5);

          //  mprogressBar4.setVisibility(View.INVISIBLE);
        }

    }
    @Override
    public void onPause(){
        super.onPause();
        if(mAuthStateListner!=null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }
    }
    public void onResume(){
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }


}