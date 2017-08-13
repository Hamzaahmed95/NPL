package myApp.hamza.npl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import static myApp.hamza.npl.ProfileActivity.ANONYMOUS;

/**
 * Created by Hamza Ahmed on 07-Aug-17.
 */

public class NewScoreCard extends Fragment {
    private ImageButton mPhotoPickerButton1;
    private ImageButton mPhotoPickerButton2;
    private ImageView imageView;
    private ImageView imageView2;
    private Button buttonEnable;
    private Button buttonEnable2;
    private TextView Runs1;
    private TextView Overs1;
    private TextView hamzaAhmed;
    private TextView Wicket1;
    private TextView Wicket2;
    private TextView Runs2;
    private TextView Overs2;
    private TextView Overs22;
    private TextView BatsmanOnCrease1;
    private TextView BatsmanOnCrease2;
    private TextView BowlerOnCrease1;
    private String mUsername;
    private TextView BatsmanRuns1;
    private TextView BatsmanRuns2;
    private TextView BatsmanRuns3;
    private TextView BatsmanBalls1;
    private TextView BatsmanBalls2;
    private TextView BatsmanBalls3;
    private static final int RC_PHOTO_PICKER = 2;
    private static final int RC_PHOTO_PICKER2 = 3;
    private TextView ball1;
    private TextView ball2;
    private TextView ball3;
    private TextView ball4;
    private TextView ball5;
    private TextView ball6;
    private Button enableButton;
    private EditText scoreInput2;
    private Button SendButton2;
    private ImageView backButton4;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mScoreDatabaseReference1;
    private DatabaseReference mScoreDatabaseReference2;
    private DatabaseReference mScoreDatabaseReference3;
    private DatabaseReference mScoreDatabaseReference4;
    private DatabaseReference mScoreDatabaseReference5;
    private DatabaseReference mScoreDatabaseReference6;
    private DatabaseReference mScoreDatabaseReference7;
    private DatabaseReference mScoreDatabaseReference8;
    private DatabaseReference mScoreDatabaseReference9;
    private DatabaseReference mScoreDatabaseReference10;
    private DatabaseReference mScoreDatabaseReference11;
    private DatabaseReference mScoreDatabaseReference12;
    private DatabaseReference mScoreDatabaseReference13;
    private DatabaseReference mScoreDatabaseReference14;
    private DatabaseReference mScoreDatabaseReference15;
    private DatabaseReference mScoreDatabaseReference16;
    private DatabaseReference mScoreDatabaseReference17;
    private DatabaseReference mScoreDatabaseReference18;
    private DatabaseReference mScoreDatabaseReference19;
    private DatabaseReference mScoreDatabaseReference20;
    private DatabaseReference mScoreDatabaseReference21;
    private DatabaseReference mScoreDatabaseReference22;
    private LinearLayout l1;
    public static final int RC_SIGN_IN =1;
    private ChildEventListener mChildEventListener;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    String name;
    private ImageButton IncrementBall1;
    private ImageButton IncrementBall2;
    private ImageButton IncrementBall3;
    private ImageButton IncrementBall4;
    private ImageButton IncrementBall5;
    private ImageButton IncrementBall6;
    private ImageButton DecrementBall1;
    private ImageButton DecrementBall2;
    private ImageButton DecrementBall3;
    private ImageButton DecrementBall4;
    private ImageButton DecrementBall5;
    private ImageButton DecrementBall6;
    private ImageButton Incrementruns1;
    private ImageButton Decrementruns1;
    private ImageButton Incrementruns2;
    private ImageButton Decrementruns2;
    private ImageButton Incrementwicket1;
    private ImageButton Incrementwicket2;
    private ImageButton Decrementwicket1;
    private ImageButton Decrementwicket2;
    private ImageButton Incrementover1;
    private ImageButton Decrementover1;
    private ImageButton Incrementover2;
    private ImageButton Decrementover2;
    private ImageButton Incrementover11;
    private ImageButton Decrementover11;
    private ImageButton Incrementover22;
    private ImageButton Decrementover22;
    private ImageButton IncrementBatsmanRuns1;
    private ImageButton DecrementBatsmanRuns1;
    private ImageButton IncrementBatsmanRuns2;
    private ImageButton DecrementBatsmanRuns2;
    private ImageButton IncrementBatsmanBalls1;
    private ImageButton DecrementBatsmanBalls1;
    private ImageButton IncrementBatsmanBalls2;
    private ImageButton DecrementBatsmanBalls2;
    private ImageButton IncrementBowlerWicket;
    private ImageButton DecrementBowlerWicket;
    private ImageButton IncrementBowlerRuns;
    private ImageButton DecrementBowlerRuns;
    private int count=0;
    private int Ball1num;
    private int RunsINCTeam1;
    private int RunsINCTeam2;
    private int WicketINCTeam1;
    private int WicketINCTeam2;
    private int Ball2num;
    private int BatsIncRuns1;
    private int BatsIncRuns2;
    private int BatsIncBalls1;
    private int BatsIncBalls2;
    private int BowlerIncBalls;
    private int BowlerIncWickets;
    private int Ball3num;
    private int OverIncOvers1;
    private int OverIncOvers2;
    private int hamza;
    private int OverIncOvers22;
    private int Ball4num;
    private int Ball4num2;
    private int Ball5num;
    private int Ball5num2;
    private int Ball6num;
    private int Ball6num2;
    private TextView hide1;
    private TextView hide2;
    private ToggleButton toggle;
    private Button matchUpdate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.new_scorecard, container, false);
        hide1 = (TextView)view.findViewById(R.id.overhide1);
        buttonEnable = (Button)view.findViewById(R.id.scorecard1);
        buttonEnable2 = (Button)view.findViewById(R.id.scorecard2);
        matchUpdate = (Button)view.findViewById(R.id.noMatchInProgress);
        hide2 = (TextView)view.findViewById(R.id.overhide2);
        mPhotoPickerButton1 = (ImageButton) view.findViewById(R.id.photoPickerTeam1);
        mPhotoPickerButton2 = (ImageButton) view.findViewById(R.id.photoPickerTeam2);
        imageView=(ImageView)view.findViewById(R.id.teamplaying1);
        imageView2=(ImageView)view.findViewById(R.id.teamplaying2);
        Runs1 = (TextView) view.findViewById(R.id.livsScoreRuns1);
        Runs2 = (TextView) view.findViewById(R.id.livsScoreRuns2);
        Overs1 = (TextView) view.findViewById(R.id.livsScoreOvers1);
        hamzaAhmed = (TextView) view.findViewById(R.id.livsScoreOvers11);
        Overs2 = (TextView) view.findViewById(R.id.livsScoreOvers2);
        Overs22 = (TextView) view.findViewById(R.id.livsScoreOvers22);
        Wicket1 = (TextView) view.findViewById(R.id.livsScoreWicket1);
        Wicket2 = (TextView) view.findViewById(R.id.livsScoreWicket2);
        BatsmanOnCrease1 = (TextView)view.findViewById(R.id.batsmanOnCrease1);
        BatsmanOnCrease2 = (TextView)view.findViewById(R.id.batsmanOnCrease2);
        BowlerOnCrease1 = (TextView)view.findViewById(R.id.bowlerOnCreas1);
        BatsmanRuns1 = (TextView)view.findViewById(R.id.runs1);
        BatsmanRuns2 = (TextView)view.findViewById(R.id.runs2);
        BatsmanRuns3 = (TextView)view.findViewById(R.id.runs3);
        BatsmanBalls1=(TextView)view.findViewById(R.id.balls1);
        BatsmanBalls2=(TextView)view.findViewById(R.id.balls2);
        BatsmanBalls3=(TextView)view.findViewById(R.id.balls3);
        ball1 = (TextView) view.findViewById(R.id.ball1);
        ball2 = (TextView) view.findViewById(R.id.ball2);
        ball3 = (TextView) view.findViewById(R.id.ball3);
        ball4 = (TextView) view.findViewById(R.id.ball4);
        ball5 = (TextView) view.findViewById(R.id.ball5);
        ball6 = (TextView) view.findViewById(R.id.ball6);
        SendButton2 = (Button)view.findViewById(R.id.batsmanSendButton);
        l1 = (LinearLayout)view.findViewById(R.id.hide);
        scoreInput2 = (EditText) view.findViewById(R.id.batsmanUpdate);
        backButton4 =(ImageView) view.findViewById(R.id.backButton4);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mScoreDatabaseReference1 = mFirebaseDatabase.getReference().child("Team1Runs");
        mScoreDatabaseReference2 = mFirebaseDatabase.getReference().child("Team1Wickets");
        mScoreDatabaseReference3 = mFirebaseDatabase.getReference().child("Team1Overs");
        mScoreDatabaseReference19 = mFirebaseDatabase.getReference().child("db");
        mScoreDatabaseReference4 = mFirebaseDatabase.getReference().child("Team2Runs");
        mScoreDatabaseReference5 = mFirebaseDatabase.getReference().child("Team2Wickets");
        mScoreDatabaseReference6 = mFirebaseDatabase.getReference().child("Team2Overs");
        mScoreDatabaseReference20 = mFirebaseDatabase.getReference().child("Team22Overs");
        mScoreDatabaseReference7 = mFirebaseDatabase.getReference().child("Batsman1Runs");
        mScoreDatabaseReference8 = mFirebaseDatabase.getReference().child("Batsman2Runs");
        mScoreDatabaseReference9 = mFirebaseDatabase.getReference().child("Batsman1Balls");
        mScoreDatabaseReference10 = mFirebaseDatabase.getReference().child("Batsman2Balls");
        mScoreDatabaseReference11 = mFirebaseDatabase.getReference().child("BowlerRuns");
        mScoreDatabaseReference12 = mFirebaseDatabase.getReference().child("BowlerWickets");
        mScoreDatabaseReference13 = mFirebaseDatabase.getReference().child("Ball1");
        mScoreDatabaseReference14 = mFirebaseDatabase.getReference().child("Ball2");
        mScoreDatabaseReference15 = mFirebaseDatabase.getReference().child("Ball3");
        mScoreDatabaseReference16 = mFirebaseDatabase.getReference().child("Ball4");
        mScoreDatabaseReference17 = mFirebaseDatabase.getReference().child("Ball5");
        mScoreDatabaseReference18 = mFirebaseDatabase.getReference().child("Ball6");
        mScoreDatabaseReference21 = mFirebaseDatabase.getReference().child("BatsmanBowler");
        mScoreDatabaseReference22 = mFirebaseDatabase.getReference().child("onOf");
        toggle = (ToggleButton) view.findViewById(R.id.toggleButton);



        Query mHouseDatabaseReference23 =mFirebaseDatabase.getReference().child("onOf").limitToLast(1);;

        mHouseDatabaseReference23.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {

                        if (issue.child("bit").getValue().equals("1")){
                            l1.setVisibility(View.GONE);
                            matchUpdate.setText("No Match is in Progress");
                        }
                        else{
                            l1.setVisibility(View.VISIBLE);
                            matchUpdate.setText("Match is in Progress");
                        }
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        buttonEnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),FullScorecard2.class);
                i.putExtra("runs1",Runs1.getText().toString());
                i.putExtra("overs1",Overs1.getText().toString());
                i.putExtra("hamza",hamzaAhmed.getText().toString());
                i.putExtra("wickets1",Wicket1.getText().toString());
                i.putExtra("id","1");
                startActivity(i);
            }
        });
        buttonEnable2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),FullScorecard3.class);
                i.putExtra("runs2",Runs2.getText().toString());
                i.putExtra("overs2",Overs2.getText().toString());
                i.putExtra("overs22",Overs22.getText().toString());
                i.putExtra("wickets2",Wicket2.getText().toString());
                i.putExtra("id","2");
                startActivity(i);
            }
        });
        Query mHouseDatabaseReference22 =mFirebaseDatabase.getReference().child("BatsmanBowler").limitToLast(1);;

        mHouseDatabaseReference22.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hamza here2"+issue.getValue());
                        BatsmanOnCrease1.setText(issue.child("batsman1").getValue().toString());
                        BatsmanOnCrease2.setText(issue.child("batsman2").getValue().toString());
                        BowlerOnCrease1.setText(issue.child("bowler1").getValue().toString());
                        //  BowlerOnCrease2.setText(issue.child("bowler2").getValue().toString());


                        //   mprogressBar.setVisibility(View.GONE);
                        //   System.out.println();
                        //array[i]=issue.child("username").getValue().toString();
                        //i++;
                    }

                    //for(int j=0;j<i;j++){
                    //  System.out.println(j+""+array[j]);
                    // }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query e1=mFirebaseDatabase.getReference().child("db").limitToLast(1);

        e1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hello->"+issue.child("ball").getValue());
                        hamza=Integer.parseInt(issue.child("ball").getValue().toString());
                        hamzaAhmed.setText(issue.child("ball").getValue().toString());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Query mHouseDatabaseReference16 =mFirebaseDatabase.getReference().child("Team1Overs").limitToLast(1);

        mHouseDatabaseReference16.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hello"+issue.child("ball").getValue());
                        OverIncOvers1=Integer.parseInt(issue.child("ball").getValue().toString());
                        Overs1.setText(issue.child("ball").getValue().toString());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Query mHouseDatabaseReference17 =mFirebaseDatabase.getReference().child("Team2Overs").limitToLast(1);

        mHouseDatabaseReference17.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hello"+issue.child("ball").getValue());
                        OverIncOvers2=Integer.parseInt(issue.child("ball").getValue().toString());
                        Overs2.setText(issue.child("ball").getValue().toString());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query mHouseDatabaseReference19 =mFirebaseDatabase.getReference().child("Team22Overs").limitToLast(1);

        mHouseDatabaseReference19.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hello"+issue.child("ball").getValue());
                        OverIncOvers22=Integer.parseInt(issue.child("ball").getValue().toString());
                        Overs22.setText(issue.child("ball").getValue().toString());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query mHouseDatabaseReference11 =mFirebaseDatabase.getReference().child("Batsman1Runs").limitToLast(1);

        mHouseDatabaseReference11.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hello"+issue.child("ball").getValue());
                        BatsIncRuns1=Integer.parseInt(issue.child("ball").getValue().toString());
                        BatsmanRuns1.setText(issue.child("ball").getValue().toString());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query mHouseDatabaseReference12 =mFirebaseDatabase.getReference().child("Batsman2Runs").limitToLast(1);

        mHouseDatabaseReference12.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hello"+issue.child("ball").getValue());
                        BatsIncRuns2=Integer.parseInt(issue.child("ball").getValue().toString());
                        BatsmanRuns2.setText(issue.child("ball").getValue().toString());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });Query mHouseDatabaseReference13 =mFirebaseDatabase.getReference().child("Batsman1Balls").limitToLast(1);

        mHouseDatabaseReference13.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hello"+issue.child("ball").getValue());
                        BatsIncBalls1=Integer.parseInt(issue.child("ball").getValue().toString());
                        BatsmanBalls1.setText(issue.child("ball").getValue().toString());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });Query mHouseDatabaseReference7 =mFirebaseDatabase.getReference().child("Batsman2Balls").limitToLast(1);

        mHouseDatabaseReference7.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hello"+issue.child("ball").getValue());
                        BatsIncBalls2=Integer.parseInt(issue.child("ball").getValue().toString());
                        BatsmanBalls2.setText(issue.child("ball").getValue().toString());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });Query mHouseDatabaseReference14 =mFirebaseDatabase.getReference().child("BowlerRuns").limitToLast(1);

        mHouseDatabaseReference14.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hello"+issue.child("ball").getValue());
                        BowlerIncBalls=Integer.parseInt(issue.child("ball").getValue().toString());
                        BatsmanRuns3.setText(issue.child("ball").getValue().toString());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });Query mHouseDatabaseReference15 =mFirebaseDatabase.getReference().child("BowlerWicket").limitToLast(1);

        mHouseDatabaseReference15.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hello"+issue.child("ball").getValue());
                        BowlerIncWickets=Integer.parseInt(issue.child("ball").getValue().toString());
                        BatsmanBalls3.setText(issue.child("ball").getValue().toString());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        Query mHouseDatabaseReference9 =mFirebaseDatabase.getReference().child("Team1Wickets").limitToLast(1);

        mHouseDatabaseReference9.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hello"+issue.child("ball").getValue());
                        WicketINCTeam1=Integer.parseInt(issue.child("ball").getValue().toString());
                        Wicket1.setText(issue.child("ball").getValue().toString());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query mHouseDatabaseReference10 =mFirebaseDatabase.getReference().child("Team2Wickets").limitToLast(1);

        mHouseDatabaseReference10.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hello"+issue.child("ball").getValue());
                        WicketINCTeam2=Integer.parseInt(issue.child("ball").getValue().toString());
                        Wicket2.setText(issue.child("ball").getValue().toString());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Query mHouseDatabaseReference8 =mFirebaseDatabase.getReference().child("Team2Runs").limitToLast(1);

        mHouseDatabaseReference8.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hello"+issue.child("ball").getValue());
                        RunsINCTeam2=Integer.parseInt(issue.child("ball").getValue().toString());
                        Runs2.setText(issue.child("ball").getValue().toString());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query mHouseDatabaseReference =mFirebaseDatabase.getReference().child("Ball1").limitToLast(1);

        mHouseDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hello"+issue.child("ball").getValue());
                        Ball1num=Integer.parseInt(issue.child("ball").getValue().toString());
                        ball1.setText(issue.child("ball").getValue().toString());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Query mHouseDatabaseReference2 =mFirebaseDatabase.getReference().child("Ball2").limitToLast(1);

        mHouseDatabaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hello"+issue.child("ball").getValue());
                        Ball2num=Integer.parseInt(issue.child("ball").getValue().toString());
                        ball2.setText(issue.child("ball").getValue().toString());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Query mHouseDatabaseReference3 =mFirebaseDatabase.getReference().child("Ball3").limitToLast(1);

        mHouseDatabaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hello"+issue.child("ball").getValue());
                        Ball3num=Integer.parseInt(issue.child("ball").getValue().toString());
                        ball3.setText(issue.child("ball").getValue().toString());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Query mHouseDatabaseReference4 =mFirebaseDatabase.getReference().child("Ball4").limitToLast(1);

        mHouseDatabaseReference4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hello"+issue.child("ball").getValue());
                        Ball4num=Integer.parseInt(issue.child("ball").getValue().toString());
                        ball4.setText(issue.child("ball").getValue().toString());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Query mHouseDatabaseReference5 =mFirebaseDatabase.getReference().child("Ball5").limitToLast(1);

        mHouseDatabaseReference5.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hello"+issue.child("ball").getValue());
                        Ball5num=Integer.parseInt(issue.child("ball").getValue().toString());
                        ball5.setText(issue.child("ball").getValue().toString());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Query mHouseDatabaseReference6 =mFirebaseDatabase.getReference().child("Ball6").limitToLast(1);

        mHouseDatabaseReference6.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hello"+issue.child("ball").getValue());
                        Ball6num=Integer.parseInt(issue.child("ball").getValue().toString());
                        ball6.setText(issue.child("ball").getValue().toString());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        IncrementBall1 = (ImageButton)view.findViewById(R.id.incrementBall1);

        IncrementBall1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(++Ball1num,1);
                mScoreDatabaseReference13.push().setValue(ball);
            }
        });

        IncrementBall2 = (ImageButton)view.findViewById(R.id.incrementBall2);
        IncrementBall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(++Ball2num,2);
                mScoreDatabaseReference14.push().setValue(ball);

            }
        });
        IncrementBall3 = (ImageButton)view.findViewById(R.id.incrementBall3);
        IncrementBall3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(++Ball3num,3);
                mScoreDatabaseReference15.push().setValue(ball);

            }
        });
        IncrementBall4 = (ImageButton)view.findViewById(R.id.incrementBall4);
        IncrementBall4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(++Ball4num,4);
                mScoreDatabaseReference16.push().setValue(ball);

            }
        });
        IncrementBall5 = (ImageButton)view.findViewById(R.id.incrementBall5);
        IncrementBall5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(++Ball5num,5);
                mScoreDatabaseReference17.push().setValue(ball);

            }
        });
        IncrementBall6 = (ImageButton)view.findViewById(R.id.incrementBall6);
        IncrementBall6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(++Ball6num,6);
                mScoreDatabaseReference18.push().setValue(ball);

            }
        });
        DecrementBall1 = (ImageButton)view.findViewById(R.id.decrementBall1);
        DecrementBall1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(--Ball1num,1);
                mScoreDatabaseReference13.push().setValue(ball);

            }
        });
        DecrementBall2 = (ImageButton)view.findViewById(R.id.decrementBall2);
        DecrementBall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(--Ball2num,2);
                mScoreDatabaseReference13.push().setValue(ball);

            }
        });
        DecrementBall3 = (ImageButton)view.findViewById(R.id.decrementBall3);
        DecrementBall3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ball ball = new Ball(--Ball3num,3);
                mScoreDatabaseReference15.push().setValue(ball);
            }
        });
        DecrementBall4 = (ImageButton)view.findViewById(R.id.decrementBall4);
        DecrementBall4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(--Ball4num,4);
                mScoreDatabaseReference16.push().setValue(ball);

            }
        });
        DecrementBall5 = (ImageButton)view.findViewById(R.id.decrementBall5);
        DecrementBall5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ball ball = new Ball(--Ball5num,5);
                mScoreDatabaseReference17.push().setValue(ball);
            }
        });
        DecrementBall6 = (ImageButton)view.findViewById(R.id.decrementBall6);
        DecrementBall6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(--Ball6num,6);
                mScoreDatabaseReference18.push().setValue(ball);

            }
        });
        Incrementruns1 = (ImageButton)view.findViewById(R.id.incrementruns1);
        Incrementruns1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ball ball = new Ball(++RunsINCTeam1,7);
                mScoreDatabaseReference1.push().setValue(ball);
            }
        });
        Incrementruns2 = (ImageButton)view.findViewById(R.id.incrementruns2);
        Incrementruns2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(++RunsINCTeam2,8);
                mScoreDatabaseReference4.push().setValue(ball);
            }
        });
        Decrementruns1 = (ImageButton)view.findViewById(R.id.decrementruns1);
        Decrementruns1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(--RunsINCTeam1,7);
                mScoreDatabaseReference1.push().setValue(ball);

            }
        });
        Decrementruns2 = (ImageButton)view.findViewById(R.id.decrementruns2);
        Decrementruns2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(--RunsINCTeam2,8);
                mScoreDatabaseReference4.push().setValue(ball);

            }
        });
        Incrementwicket1 = (ImageButton)view.findViewById(R.id.incrementwicket1);
        Incrementwicket1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(++WicketINCTeam1,9);
                mScoreDatabaseReference2.push().setValue(ball);
            }
        });
        Incrementwicket2 = (ImageButton)view.findViewById(R.id.incrementwicket2);
        Incrementwicket2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(++WicketINCTeam2,10);
                mScoreDatabaseReference5.push().setValue(ball);
            }
        });

        Decrementwicket1 = (ImageButton)view.findViewById(R.id.decrementwicket1);
        Decrementwicket1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(--WicketINCTeam1,9);
                mScoreDatabaseReference2.push().setValue(ball);
            }
        });
        Decrementwicket2 = (ImageButton)view.findViewById(R.id.decrementwicket2);
        Decrementwicket2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(--WicketINCTeam2,10);
                mScoreDatabaseReference5.push().setValue(ball);

            }
        });
        Incrementover1 = (ImageButton)view.findViewById(R.id.incrementover1);
        Incrementover1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(++OverIncOvers1,17);
                mScoreDatabaseReference3.push().setValue(ball);

            }
        });
        Incrementover2 = (ImageButton)view.findViewById(R.id.incrementover2);
        Incrementover2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(++OverIncOvers2,18);
                mScoreDatabaseReference6.push().setValue(ball);
            }
        });
        Decrementover1 = (ImageButton)view.findViewById(R.id.decrementover1);
        Decrementover1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(--OverIncOvers1,17);
                mScoreDatabaseReference3.push().setValue(ball);
            }
        });
        Decrementover2 = (ImageButton)view.findViewById(R.id.decrementover2);
        Decrementover2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(--OverIncOvers2,18);
                mScoreDatabaseReference6.push().setValue(ball);
            }
        });

        Incrementover11 = (ImageButton)view.findViewById(R.id.incrementover11);
        Incrementover11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(++hamza,19);
                mScoreDatabaseReference19.push().setValue(ball);

            }
        });
        Incrementover22 = (ImageButton)view.findViewById(R.id.incrementover22);
        Incrementover22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(++OverIncOvers22,20);
                mScoreDatabaseReference20.push().setValue(ball);

            }
        });
        Decrementover11 = (ImageButton)view.findViewById(R.id.decrementover11);
        Decrementover11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(--hamza,19);
                mScoreDatabaseReference19.push().setValue(ball);
            }
        });
        Decrementover22 = (ImageButton)view.findViewById(R.id.decrementover22);
        Decrementover22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(--OverIncOvers22,20);
                mScoreDatabaseReference20.push().setValue(ball);
            }
        });


        IncrementBatsmanRuns1 = (ImageButton)view.findViewById(R.id.incrementBatsmanRuns1);
        IncrementBatsmanRuns1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ball ball = new Ball(++BatsIncRuns1,11);
                mScoreDatabaseReference7.push().setValue(ball);
            }
        });
        IncrementBatsmanRuns2 = (ImageButton)view.findViewById(R.id.incrementBatsmanRuns2);
        IncrementBatsmanRuns2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ball ball = new Ball(++BatsIncRuns2,12);
                mScoreDatabaseReference8.push().setValue(ball);
            }
        });
        DecrementBatsmanRuns1 = (ImageButton)view.findViewById(R.id.decrementBatsmanRuns1);
        DecrementBatsmanRuns1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(--BatsIncRuns1,11);
                mScoreDatabaseReference7.push().setValue(ball);
            }
        });
        DecrementBatsmanRuns2 = (ImageButton)view.findViewById(R.id.decrementBatsmanRuns2);
        DecrementBatsmanRuns2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(--BatsIncRuns2,12);
                mScoreDatabaseReference8.push().setValue(ball);
            }
        });
        IncrementBatsmanBalls1 = (ImageButton)view.findViewById(R.id.incrementBatsmanBalls1);
        IncrementBatsmanBalls1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(++BatsIncRuns1,13);
                mScoreDatabaseReference9.push().setValue(ball);

            }
        });
        IncrementBatsmanBalls2 = (ImageButton)view.findViewById(R.id.incrementBatsmanBalls2);
        IncrementBatsmanBalls2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(++BatsIncBalls2,14);
                mScoreDatabaseReference10.push().setValue(ball);

            }
        });
        DecrementBatsmanBalls1 = (ImageButton)view.findViewById(R.id.decrementBatsmanBalls1);
        DecrementBatsmanBalls1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(--BatsIncBalls1,13);
                mScoreDatabaseReference9.push().setValue(ball);
            }
        });
        DecrementBatsmanBalls2 = (ImageButton)view.findViewById(R.id.decrementBatsmanBalls2);
        DecrementBatsmanBalls2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(--BatsIncBalls2,14);
                mScoreDatabaseReference10.push().setValue(ball);
            }
        });
        IncrementBowlerRuns = (ImageButton)view.findViewById(R.id.incrementBowlerRuns);
        IncrementBowlerRuns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(++BowlerIncBalls,15);
                mScoreDatabaseReference11.push().setValue(ball);
            }
        });
        IncrementBowlerWicket = (ImageButton)view.findViewById(R.id.incrementBowlerWicket);
        IncrementBowlerWicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ball ball = new Ball(++BowlerIncWickets,16);
                mScoreDatabaseReference12.push().setValue(ball);
            }
        });
        DecrementBowlerRuns  = (ImageButton)view.findViewById(R.id.decrementBowlerRuns);
        DecrementBowlerRuns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(--BowlerIncBalls,15);
                mScoreDatabaseReference11.push().setValue(ball);
            }
        });
        DecrementBowlerWicket = (ImageButton)view.findViewById(R.id.decrementBowlerWicket);
        DecrementBowlerWicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(--BowlerIncWickets,16);
                mScoreDatabaseReference12.push().setValue(ball);
            }
        });


        backButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), OptionsActivity.class);
                startActivity(i);
            }
        });

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    onOff onOff = new onOff("1");
                    mScoreDatabaseReference22.push().setValue(onOff);

                } else {
                    // The toggle is disabled
                    onOff onOff = new onOff("0");

                    mScoreDatabaseReference22.push().setValue(onOff);

                }
            }
        });
        SendButton2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String score = scoreInput2.getText().toString();
                        String[] arr = score.split("-");
                        String Batsman1 = arr[0];
                        String Batsman2 = arr[1];
                        String Bowler1 = arr[2];
                        String Bowler2 = arr[3];
                        BatsmanBowler batsmanBowler = new BatsmanBowler(Batsman1,Batsman2,Bowler1,Bowler2,null,null);
                        mScoreDatabaseReference21.push().setValue(batsmanBowler);
                        scoreInput2.setText("");
                        BatsmanOnCrease1.setText(Batsman1);
                        BatsmanOnCrease2.setText(Batsman2);
                        BowlerOnCrease1.setText(Bowler1);

                    }
                });

        mUsername = ANONYMOUS;

        if(mPhotoPickerButton1!=null)
            mPhotoPickerButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: Fire an intent to show an image picker
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    // intent.setType("image/png");
                    intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                    startActivityForResult(intent.createChooser(intent,"Complete action using"),RC_PHOTO_PICKER);

                }
            });
        if(mPhotoPickerButton2!=null)
            mPhotoPickerButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: Fire an intent to show an image picker
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    // intent.setType("image/png");
                    intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                    startActivityForResult(intent.createChooser(intent,"Complete action using"),RC_PHOTO_PICKER2);

                }
            });
        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //user is signed in

                    onSignedInInitialize(user.getDisplayName());
                    Log.d("user: ", user.getDisplayName());
                    name=user.getDisplayName();
                    if (!user.getDisplayName().equals("K142805 Hamza Ahmed")) {
                        scoreInput2.setVisibility(View.GONE);
                        mPhotoPickerButton1.setVisibility(View.GONE);
                        mPhotoPickerButton2.setVisibility(View.GONE);
                        IncrementBall1.setVisibility(View.GONE);
                        IncrementBall2.setVisibility(View.GONE);
                        IncrementBall3.setVisibility(View.GONE);
                        IncrementBall4.setVisibility(View.GONE);
                        IncrementBall5.setVisibility(View.GONE);
                        IncrementBall6.setVisibility(View.GONE);
                        DecrementBall1.setVisibility(View.GONE);
                        DecrementBall2.setVisibility(View.GONE);
                        DecrementBall3.setVisibility(View.GONE);
                        DecrementBall4.setVisibility(View.GONE);
                        DecrementBall5.setVisibility(View.GONE);
                        DecrementBall6.setVisibility(View.GONE);
                        Incrementruns1.setVisibility(View.GONE);
                        Decrementruns1.setVisibility(View.GONE);
                        Incrementruns2.setVisibility(View.GONE);
                        Decrementruns2.setVisibility(View.GONE);
                        Incrementwicket1.setVisibility(View.GONE);
                        Decrementwicket1.setVisibility(View.GONE);
                        Incrementwicket2.setVisibility(View.GONE);
                        Decrementwicket2.setVisibility(View.GONE);
                        Incrementover1.setVisibility(View.GONE);
                        Incrementover2.setVisibility(View.GONE);
                        Decrementover1.setVisibility(View.GONE);
                        Decrementover2.setVisibility(View.GONE);
                        Incrementover11.setVisibility(View.GONE);
                        Incrementover22.setVisibility(View.GONE);
                        Decrementover11.setVisibility(View.GONE);
                        Decrementover22.setVisibility(View.GONE);
                        IncrementBatsmanRuns1.setVisibility(View.GONE);
                        IncrementBatsmanRuns2.setVisibility(View.GONE);
                        IncrementBatsmanBalls1.setVisibility(View.GONE);
                        IncrementBatsmanBalls2.setVisibility(View.GONE);
                        DecrementBatsmanRuns1.setVisibility(View.GONE);
                        DecrementBatsmanRuns2.setVisibility(View.GONE);
                        DecrementBatsmanBalls1.setVisibility(View.GONE);
                        DecrementBatsmanBalls2.setVisibility(View.GONE);
                        IncrementBatsmanRuns1.setVisibility(View.GONE);
                        IncrementBatsmanRuns2.setVisibility(View.GONE);
                        IncrementBatsmanBalls1.setVisibility(View.GONE);
                        IncrementBatsmanBalls2.setVisibility(View.GONE);
                        IncrementBowlerWicket.setVisibility(View.GONE);
                        DecrementBowlerWicket.setVisibility(View.GONE);
                        IncrementBowlerRuns.setVisibility(View.GONE);
                        DecrementBowlerRuns.setVisibility(View.GONE);
                        toggle.setVisibility(View.GONE);
                        SendButton2.setVisibility(View.GONE);
                    }
                    else{
                        hide2.setVisibility(View.GONE);
                        hide1.setVisibility(View.GONE);
                    }
                } else {
                    //user is signed out
                    onSignedOutInitialize();

                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setProviders(
                                            AuthUI.EMAIL_PROVIDER,
                                            AuthUI.GOOGLE_PROVIDER
                                    ).build(),
                            RC_SIGN_IN);

                }
            }

            ;
        };


        return view;
    }
    private void  onSignedInInitialize(String username){
        mUsername = username;
        attachDatabaseReadListener();

    }
    private void  onSignedOutInitialize(){
        mUsername = ANONYMOUS;

        detachDatabaseReadListener();
    }

    private void attachDatabaseReadListener(){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Ball ball = dataSnapshot.getValue(Ball.class);
                    //System.out.println(ball.toString());
                    System.out.println(ball.getTurn());
                   if(ball.getTurn() ==1) {
                       ball1.setText(String.valueOf(ball.getBall()));
                   } else if(ball.getTurn() ==2) {
                       ball2.setText(String.valueOf(ball.getBall()));
                   } else if(ball.getTurn() ==3) {
                       ball3.setText(String.valueOf(ball.getBall()));
                   } else if(ball.getTurn() ==4) {
                       ball4.setText(String.valueOf(ball.getBall()));
                   } else if(ball.getTurn() ==5) {
                       ball5.setText(String.valueOf(ball.getBall()));
                   } else if(ball.getTurn() ==6) {
                       ball6.setText(String.valueOf(ball.getBall()));
                   }else if(ball.getTurn() ==7) {
                       Runs1.setText(String.valueOf(ball.getBall()));
                   }else if(ball.getTurn() ==8) {
                       Runs2.setText(String.valueOf(ball.getBall()));
                   }
                   else if(ball.getTurn() ==9) {
                       Wicket1.setText(String.valueOf(ball.getBall()));
                   }else if(ball.getTurn() ==10) {
                       Wicket2.setText(String.valueOf(ball.getBall()));
                   }else if(ball.getTurn() ==11) {
                       BatsmanRuns1.setText(String.valueOf(ball.getBall()));
                   }else if(ball.getTurn() ==12) {
                       BatsmanRuns2.setText(String.valueOf(ball.getBall()));
                   }else if(ball.getTurn() ==13) {
                       BatsmanBalls1.setText(String.valueOf(ball.getBall()));
                   }else if(ball.getTurn() ==14) {
                       BatsmanBalls2.setText(String.valueOf(ball.getBall()));
                   }else if(ball.getTurn() ==15) {
                       BatsmanRuns3.setText(String.valueOf(ball.getBall()));
                   }else if(ball.getTurn() ==16) {
                       BatsmanBalls3.setText(String.valueOf(ball.getBall()));
                   }
                   else if(ball.getTurn() ==17) {
                       Overs1.setText(String.valueOf(ball.getBall()));
                   }else if(ball.getTurn() ==18) {
                       Overs2.setText(String.valueOf(ball.getBall()));
                   }else if(ball.getTurn() ==19) {
                       hamzaAhmed.setText(String.valueOf(ball.getBall()));
                   }else if(ball.getTurn() ==20) {
                       Overs22.setText(String.valueOf(ball.getBall()));
                   }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    //  FriendlyMessage f =dataSnapshot.getValue(FriendlyMessage.class);
                    // Log.d("ooo = ",f.getName());
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            mScoreDatabaseReference1.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference2.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference3.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference4.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference5.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference6.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference7.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference8.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference9.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference10.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference11.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference12.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference13.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference14.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference15.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference16.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference17.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference18.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference19.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference20.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference21.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference22.addChildEventListener(mChildEventListener);
        }
    }
    private void detachDatabaseReadListener(){
        if(mChildEventListener!=null)
            mScoreDatabaseReference1.removeEventListener(mChildEventListener);
        mScoreDatabaseReference2.removeEventListener(mChildEventListener);
        mScoreDatabaseReference3.removeEventListener(mChildEventListener);
        mScoreDatabaseReference4.removeEventListener(mChildEventListener);
        mScoreDatabaseReference5.removeEventListener(mChildEventListener);
        mScoreDatabaseReference6.removeEventListener(mChildEventListener);
        mScoreDatabaseReference7.removeEventListener(mChildEventListener);
        mScoreDatabaseReference8.removeEventListener(mChildEventListener);
        mScoreDatabaseReference9.removeEventListener(mChildEventListener);
        mScoreDatabaseReference10.removeEventListener(mChildEventListener);
        mScoreDatabaseReference11.removeEventListener(mChildEventListener);
        mScoreDatabaseReference12.removeEventListener(mChildEventListener);
        mScoreDatabaseReference13.removeEventListener(mChildEventListener);
        mScoreDatabaseReference14.removeEventListener(mChildEventListener);
        mScoreDatabaseReference15.removeEventListener(mChildEventListener);
        mScoreDatabaseReference16.removeEventListener(mChildEventListener);
        mScoreDatabaseReference17.removeEventListener(mChildEventListener);
        mScoreDatabaseReference18.removeEventListener(mChildEventListener);
        mScoreDatabaseReference19.removeEventListener(mChildEventListener);
        mScoreDatabaseReference20.removeEventListener(mChildEventListener);
        mScoreDatabaseReference21.removeEventListener(mChildEventListener);
        mScoreDatabaseReference22.removeEventListener(mChildEventListener);
        mChildEventListener=null;
    }
    @Override
    public void onPause(){
        super.onPause();
        if(mAuthStateListner!=null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }
        detachDatabaseReadListener();
    }
    @Override
    public void onResume(){
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }



}
