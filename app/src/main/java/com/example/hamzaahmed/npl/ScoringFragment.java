package com.example.hamzaahmed.npl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import static com.example.hamzaahmed.npl.ProfileActivity.ANONYMOUS;
import static com.example.hamzaahmed.npl.ProfileActivity.DEFAULT_MSG_LENGTH_LIMIT;

/**
 * Created by Hamza Ahmed on 13-Jul-17.
 */

public class ScoringFragment extends Fragment {

    private TextView Runs1;
    private TextView Overs1;
    private TextView Wicket1;
    private TextView Runs2;
    private TextView Overs2;
    private TextView ball1;
    private TextView ball2;
    private TextView ball3;
    private TextView ball4;
    private TextView ball5;
    private TextView ball6;
    private Button getScoreCard;

    private TextView Wicket2;
    private EditText scoreInput;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mScoreDatabaseReference;
    public static final int RC_SIGN_IN =1;
    private ChildEventListener mChildEventListener;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private String mUsername;
    private Button SendButton;
    private ImageView backButton4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.scorecard, container, false);
        Runs1 = (TextView) view.findViewById(R.id.livsScoreRuns1);
        Runs2 = (TextView) view.findViewById(R.id.livsScoreRuns2);
        Overs1 = (TextView) view.findViewById(R.id.livsScoreOvers1);
        Overs2 = (TextView) view.findViewById(R.id.livsScoreOvers2);
        Wicket1 = (TextView) view.findViewById(R.id.livsScoreWicket1);
        Wicket2 = (TextView) view.findViewById(R.id.livsScoreWicket2);
        Runs1 = (TextView) view.findViewById(R.id.livsScoreRuns1);
        Runs1 = (TextView) view.findViewById(R.id.livsScoreRuns1);
        scoreInput = (EditText) view.findViewById(R.id.scoreUpdate);
        SendButton = (Button) view.findViewById(R.id.scoreSendButton);
        ball1 = (TextView) view.findViewById(R.id.ball1);
        ball2 = (TextView) view.findViewById(R.id.ball2);
        ball3 = (TextView) view.findViewById(R.id.ball3);
        ball4 = (TextView) view.findViewById(R.id.ball4);
        ball5 = (TextView) view.findViewById(R.id.ball5);
        ball6 = (TextView) view.findViewById(R.id.ball6);
        backButton4 =(ImageView) view.findViewById(R.id.backButton4);

        backButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), OptionsActivity.class);
                startActivity(i);
            }
        });

        getScoreCard = (Button) view.findViewById(R.id.getFullScoreCard);
        getScoreCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), FullScoreCard.class);
                startActivity(i);
            }
        });


        mUsername = ANONYMOUS;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mScoreDatabaseReference = mFirebaseDatabase.getReference().child("score");
        SendButton.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String score = scoreInput.getText().toString();
                String[] arr = score.split("-");
                String runs = arr[0];
                String wicket = arr[1];
                String overs = arr[2];
                String team = arr[3];
                String balls = arr[4];
                String ballNo = arr[5];
                LiveScore liveScore = new LiveScore(Integer.parseInt(runs), Integer.parseInt(wicket), Float.parseFloat(overs), 1, Integer.parseInt(team), Integer.parseInt(balls), Integer.parseInt(ballNo));
                mScoreDatabaseReference.push().setValue(liveScore);
                scoreInput.setText("");
                if (Integer.parseInt(team) == 1) {
                    Runs1.setText(runs);
                    Wicket1.setText(wicket);
                    Overs1.setText(overs);
                } else {

                    Runs2.setText(runs);
                    Wicket2.setText(wicket);
                    Overs2.setText(overs);
                }
                if (Integer.parseInt(ballNo) == 1) {
                    ball1.setText(balls);
                } else if (Integer.parseInt(ballNo) == 2) {
                    ball2.setText(balls);
                } else if (Integer.parseInt(ballNo) == 3) {
                    ball3.setText(balls);
                } else if (Integer.parseInt(ballNo) == 4) {
                    ball4.setText(balls);
                } else if (Integer.parseInt(ballNo) == 5) {
                    ball5.setText(balls);
                } else if (Integer.parseInt(ballNo) == 6) {
                    ball6.setText(balls);
                }


            }
        });
        if (scoreInput != null) {
            scoreInput.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.toString().trim().length() > 0) {
                        SendButton.setEnabled(true);
                    } else {
                        SendButton.setEnabled(false);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }

            });
            scoreInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        }


        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //user is signed in
                    onSignedInInitialize(user.getDisplayName());
                    Log.d("user: ", user.getDisplayName());
                    if (!user.getDisplayName().equals("K142805 Hamza Ahmed")) {
                        scoreInput.setVisibility(View.GONE);
                        SendButton.setVisibility(View.GONE);
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
                    LiveScore liveScore = dataSnapshot.getValue(LiveScore.class);
                    String score = scoreInput.getText().toString();
                    if(liveScore.getTeam()!=null) {
                        if (liveScore.getTeam() == 1) {
                            Runs1.setText(String.valueOf(liveScore.getRuns()));
                            Wicket1.setText(String.valueOf(liveScore.getWicket()));
                            Overs1.setText(String.valueOf(liveScore.getOvers()));
                        } else {
                            Runs2.setText(String.valueOf(liveScore.getRuns()));
                            Wicket2.setText(String.valueOf(liveScore.getWicket()));
                            Overs2.setText(String.valueOf(liveScore.getOvers()));
                        }
                      }
                    if(liveScore.getBallNo()!=null) {
                        if (liveScore.getBallNo() == 1) {
                            ball1.setText(String.valueOf(liveScore.getBalls()));
                        } else if (liveScore.getBallNo() == 2) {
                            ball2.setText(String.valueOf(liveScore.getBalls()));
                        } else if (liveScore.getBallNo() == 3) {
                            ball3.setText(String.valueOf(liveScore.getBalls()));
                        } else if (liveScore.getBallNo() == 4) {
                            ball4.setText(String.valueOf(liveScore.getBalls()));
                        } else if (liveScore.getBallNo() == 5) {
                            ball5.setText(String.valueOf(liveScore.getBalls()));
                        } else if (liveScore.getBallNo() == 6) {
                            ball6.setText(String.valueOf(liveScore.getBalls()));
                        }
                    }


                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    FriendlyMessage f =dataSnapshot.getValue(FriendlyMessage.class);
                    Log.d("ooo = ",f.getName());
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
            mScoreDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }
    private void detachDatabaseReadListener(){
        if(mChildEventListener!=null)
            mScoreDatabaseReference.removeEventListener(mChildEventListener);
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
