package com.example.hamzaahmed.npl;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.List;

import static com.example.hamzaahmed.npl.ProfileActivity.ANONYMOUS;

/**
 * Created by Hamza Ahmed on 15-Jul-17.
 */

public class MatchesFragment extends Fragment {

    private RecyclerView recyclerView;
    private MatchAdapter adapter;
    private ImageView backImageView;
    private EditText result;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mRecentMatchesDatabaseReference;
    public static final int RC_SIGN_IN =1;
    private ChildEventListener mChildEventListener;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private String mUsername;
    private Button resultButton;
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.match,container,false);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mRecentMatchesDatabaseReference = mFirebaseDatabase.getReference().child("recent_matches");
        mUsername = ANONYMOUS;

        recyclerView = (RecyclerView)view.findViewById(R.id.listOfMatches);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Query mHouseDatabaseReference2 =mFirebaseDatabase.getReference().child("recent_matches");

        mHouseDatabaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println(issue.child("matchResult").getValue());

                        Match match = new Match();
                        match.setImage1Id(Integer.parseInt(issue.child("image1Id").getValue().toString()));
                        match.setImage2Id(Integer.parseInt(issue.child("image2Id").getValue().toString()));
                        match.setMatchDate(issue.child("matchDate").getValue().toString());
                        match.setMatchNo(issue.child("matchNo").getValue().toString());
                        match.setVenue(issue.child("venue").getValue().toString());
                        match.setMatchResult(issue.child("matchResult").getValue().toString());
                        mMatch.add(match);
                        //   System.out.println();
                        //array[i]=issue.child("username").getValue().toString();
                        //i++;
                    }
                    adapter.notifyDataSetChanged();
                    adapter = new MatchAdapter(getmMatch());
                    recyclerView.setAdapter(adapter);

                    //for(int j=0;j<i;j++){
                      //  System.out.println(j+""+array[j]);
                   // }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        backImageView =(ImageView)view.findViewById(R.id.backButton3);
        result = (EditText)view.findViewById(R.id.result);
        resultButton =(Button)view.findViewById(R.id.resultButton);
        mMatch = new ArrayList<>();

        resultButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String matchResult=result.getText().toString();

                String [] array1 = matchResult.split("-");

                // System.out.println(result);
                //      System.out.println(match2.getMatchResult());
                for(int i = 0; i< MatchNo.length; i++){
                 //   System.out.println(array1[i]);
                    Match match = new Match();
                    match.setImage1Id(Team1[i]);
                    match.setImage2Id(Team2[i]);
                    match.setMatchDate(MatchDate[i]);
                    match.setMatchNo(MatchNo[i]);
                    match.setVenue(MatchVenue[i]);
                    match.setMatchResult(array1[i]);
                    mRecentMatchesDatabaseReference.push().setValue(match);
                    result.setText("");
                    mMatch.add(match);
                }
                adapter.notifyDataSetChanged();
                /*for(String s:mMatch){

                }*/

                //System.out.println(result.getText().toString());

            }
        });

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),OptionsActivity.class);
                startActivity(i);
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
                    if (!user.getDisplayName().equals("K142805 Hamza Ahmed")) {
                        result.setVisibility(View.GONE);
                        resultButton.setVisibility(View.GONE);
                    }
                    adapter = new MatchAdapter(getmMatch());
                    recyclerView.setAdapter(adapter);

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
    public List<Match> getmMatch(){

        return mMatch;
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
                    Match match = dataSnapshot.getValue(Match.class);
                   // System.out.println("match "+match.);

                    for (DataSnapshot issue : dataSnapshot.getChildren()) {

                     //   System.out.println("hamza"+issue.child("matchResult").getValue());
                      //  System.out.println("line");

                    }
                    adapter = new MatchAdapter(getmMatch());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Match f =dataSnapshot.getValue(Match.class);


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
            mRecentMatchesDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }

    private void detachDatabaseReadListener(){
        if(mChildEventListener!=null)
            mRecentMatchesDatabaseReference.removeEventListener(mChildEventListener);
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
