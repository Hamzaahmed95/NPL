package com.example.hamzaahmed.npl;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.facebook.share.widget.SendButton;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Hamza Ahmed on 16-Jul-17.
 */

public class OptionsFragment extends Fragment {
    AnimatorSet set;
    AnimatorSet set2;
    private TextView name;
    private LinearLayout Chat;
    private LinearLayout getScore;
    private LinearLayout getMatches;
    private LinearLayout getTeams;
    private LinearLayout Chat1;
    private LinearLayout getScore1;
    private LinearLayout getMatches1;
    private LinearLayout getTeams1;
    private LinearLayout MOM;
    private ImageView signout;

    AnimatorSet set3;
    private FirebaseDatabase mFirebaseDatabase;

    private DatabaseReference mMessageDatabaseReference;
    private DatabaseReference mUsersDatabaseReference;
    public static final int RC_SIGN_IN =1;
    public static final String ANONYMOUS = "anonymous";
    private ChildEventListener mChildEventListener;
    private String mUsername;
    private FirebaseAuth mFirebaseAuth;
    private String UserName;
    private FirebaseAuth.AuthStateListener mAuthStateListner;

    private FirebaseStorage firebaseStorage;

    private StorageReference mChatPhotoStorageReference;


    AnimatorSet set4;
    AnimatorSet set5;
    AnimatorSet set6;
    LinearLayout layout01;
    AnimatorSet set7;
    AnimatorSet set8;
    AnimatorSet set9;
    Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.options, container, false);
        Chat = (LinearLayout) view.findViewById(R.id.layout1);
        getScore = (LinearLayout) view.findViewById(R.id.layout2);
        Chat1 = (LinearLayout) view.findViewById(R.id.layout5);
        getScore1 = (LinearLayout) view.findViewById(R.id.layout6);
        signout=(ImageView)view.findViewById(R.id.logout);
        layout01 = (LinearLayout)view.findViewById(R.id.layout01);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();

        name=(TextView)view.findViewById(R.id.optionUsername);
        firebaseStorage = FirebaseStorage.getInstance();
        mUsername = ANONYMOUS;
        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("messages");
        mChatPhotoStorageReference =firebaseStorage.getReference().child("chat_photos");
        mUsersDatabaseReference = mFirebaseDatabase.getReference().child("users");
        Log.d("oncreate ",mMessageDatabaseReference.getDatabase().toString());

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance().signOut(getActivity());
            }
        });


        getMatches = (LinearLayout) view.findViewById(R.id.layout3);
        getTeams = (LinearLayout) view.findViewById(R.id.layout4);
        getMatches1 = (LinearLayout) view.findViewById(R.id.layout7);
        getTeams1 = (LinearLayout) view.findViewById(R.id.layout8);
        MOM = (LinearLayout) view.findViewById(R.id.layout9);


        MOM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),MOM.class);
                i.putExtra("username",name.getText());
                startActivity(i);
            }
        });

        getTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),Teams.class);
                startActivity(i);
            }
        });

        Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),ProfileActivity.class);
                startActivity(i);
            }
        });

        getScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),ScoringActivity.class);
                startActivity(i);
            }
        });
        getScore1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),PointsTableActivity.class);
                i.putExtra("username",name.getText());
                startActivity(i);
            }
        });



        getMatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),RecentMatches.class);
                startActivity(i);
            }
        });



        Bundle extra =getActivity().getIntent().getExtras();
       if(extra!=null) {
            String house = extra.getString("batch");
       if(house.equals("green"))
            //Log.d("house",house);
             if(house.equals("green")){
                 layout01.setBackgroundColor(getResources().getColor(R.color.greenshades1));

                Log.d("hzma green","house");
            }
            else if(house.equals("lightBlue")){

                 layout01.setBackgroundResource(R.color.lightBlue);
                 layout01.setBackgroundColor(getResources().getColor(R.color.lightBlue));

                Log.d("lightBlue ",house);

            }
            else if(house.equals("purple")){
                 layout01.setBackgroundColor(getResources().getColor(R.color.purple));

                Log.d("purple ",house);
            }
            else if(house.equals("red")){
                 layout01.setBackgroundColor(getResources().getColor(R.color.redshades1));
                Log.d("red ",house);

            }
            else if(house.equals("peach")){
                 layout01.setBackgroundColor(getResources().getColor(R.color.peach));

                Log.d("peach ",house);

            }
            else if(house.equals("yellow")){
                 layout01.setBackgroundColor(getResources().getColor(R.color.yellow));

                Log.d("yellow ",house);

            }
            else{

            }

        }

            mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user!=null){
                    //user is signed in
                    onSignedInInitialize(user.getDisplayName());
                    name.setText(user.getDisplayName());

                    //name.setText(user.getDisplayName().toUpperCase());
                    Log.d("hamza here","this");
                    Log.d("check",user.getDisplayName().substring(2,3));

                }else{
                    //user is signed out
                    onSignedOutInitialize();

                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setTheme(R.style.FirebaseLoginTheme)
                                    .setLogo(R.drawable.wb5)
                                    .setProviders(
                                            AuthUI.GOOGLE_PROVIDER
                                    ).build(),
                            RC_SIGN_IN);

                }
            };
        };

        return view;


    }

    @Override
    public void onPause(){
        super.onPause();
        if(mAuthStateListner!=null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }
        detachDatabaseReadListener();
    }
    public void onStart() {
        super.onStart();



        set = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip);
        set2 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip1);
        set.setTarget(Chat);
        set2.setTarget(getScore);
        set.start();
        set2.start();
        set3 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip2);
        set4 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip4);
        set3.setTarget(getMatches);
        set4.setTarget(getTeams);
        set3.start();
        set4.start();
        set5 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip3);
        set6 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip5);
        set5.setTarget(Chat1);
        set6.setTarget(getScore1);
        set5.start();
        set6.start();
        set7 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip6);
        set8 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip7);
        set7.setTarget(getMatches1);
        set8.setTarget(getTeams1);
        set7.start();
        set8.start();
        set9 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip8);
        set9.setTarget(MOM);
        set9.start();


    }
    @Override
    public void onResume(){
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
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
                    FriendlyMessage friendlyMessage = dataSnapshot.getValue(FriendlyMessage.class);
                    String splited = friendlyMessage.getName();


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
            mMessageDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }
    private void detachDatabaseReadListener(){
        if(mChildEventListener!=null)
            mMessageDatabaseReference.removeEventListener(mChildEventListener);
        mChildEventListener=null;
    }

}