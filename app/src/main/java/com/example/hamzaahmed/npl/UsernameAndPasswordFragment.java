package com.example.hamzaahmed.npl;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Map;

import static com.example.hamzaahmed.npl.OptionsFragment.ANONYMOUS;

/**
 * Created by Hamza Ahmed on 19-Jul-17.
 */

public class UsernameAndPasswordFragment extends Fragment {

    EditText FavPlayer;
    TextView Username;
    Spinner BestTeam;
    public static final int RC_SIGN_IN =1;
    Button SendButtonQuestion;
    Spinner BestPlayer;


    private FirebaseDatabase mFirebaseDatabase;

    private DatabaseReference mHouseDatabaseReference;
    private DatabaseReference mHouseDatabaseReference2;
    private ChildEventListener mChildEventListener;
    private String mUsername;
    private String array[];
    private String house;
    private String others;
    private String favouriteTeam;
    private String favouriteBatsman;
    int i;
    private FirebaseAuth mFirebaseAuth;

    private FirebaseAuth.AuthStateListener mAuthStateListner;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.username_password,container,false);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        array = new String[100];
        i=0;
        mHouseDatabaseReference =mFirebaseDatabase.getReference().child("house");
        Query mHouseDatabaseReference2 =mFirebaseDatabase.getReference().child("house").orderByChild("username");

        mHouseDatabaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println(issue.child("username").getValue().equals(mUsername));
                        array[i]=issue.child("username").getValue().toString();
                        i++;

                    }
                    for(int j=0;j<i;j++){
                        System.out.println(j+""+array[j]);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mUsername = ANONYMOUS;
        

        FavPlayer = (EditText)view.findViewById(R.id.FavPlayer);
        Username=(TextView) view.findViewById(R.id.Username);
        BestTeam = (Spinner) view.findViewById(R.id.favTeam);
        BestPlayer = (Spinner) view.findViewById(R.id.FavPlayer2);

        SendButtonQuestion = (Button) view.findViewById(R.id.sendButtonQuesion);
        final String[] items = new String[]{"Nawait United", "Nawait Aces", "Nawait Royals", "Shan-e-Nawait", "Nawait Sultan", "Nawait Janbaz"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_style, items);
        Log.d("quantity",""+adapter);

        BestTeam.setAdapter(adapter);
        BestTeam.setPrompt("select");
        final String[] items2 = new String[]{"select","Bidchol Saqib", "Haji Ameen Ali", "Khateeb Mairaj","Sunny Adnan","others"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.spinner_style, items2);
        Log.d("quantity",""+adapter2);

        BestTeam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                switch (position) {

                    case 0:
                        // set editbox visible
                        Log.d("case : "," "+items[position]);
                        favouriteTeam=items[position];
                        house="green";

                        break;
                    case 1:
                        // set editbox invivible
                        Log.d("case : "," "+position);
                        favouriteTeam=items[position];
                        house="lightBlue";

                        break;
                    case 2:
                        // set editbox invivible
                        Log.d("case : "," "+position);
                        favouriteTeam=items[position];
                        house="purple";
                        break;
                    case 3:
                        Log.d("case : "," "+position);
                        favouriteTeam=items[position];
                        house="red";
                        break;
                    case 4:
                        house="peach";
                        favouriteTeam=items[position];

                        break;
                    case 5:
                        house="yellow";
                        favouriteTeam=items[position];
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // set editbox invivible
                FavPlayer.setVisibility(View.VISIBLE);

            }
        });






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
                        favouriteBatsman=items2[position];
                        SendButtonQuestion.setEnabled(false);
                        SendButtonQuestion.setTextColor(Color.RED);
                        break;
                    case 1:
                        // set editbox invivible
                        Log.d("case : "," "+position);
                        favouriteBatsman=items2[position];

                        SendButtonQuestion.setTextColor(Color.BLACK);
                        SendButtonQuestion.setEnabled(true);
                        break;
                    case 2:
                        // set editbox invivible
                        Log.d("case : "," "+position);
                        favouriteBatsman=items2[position];
                        SendButtonQuestion.setTextColor(Color.BLACK);
                        SendButtonQuestion.setEnabled(true);
                        break;
                    case 3:
                        Log.d("case : "," "+position);
                        favouriteBatsman=items2[position];
                        SendButtonQuestion.setTextColor(Color.BLACK);
                        SendButtonQuestion.setEnabled(true);
                        break;
                    case 4:
                        Log.d("case : "," "+position);
                        favouriteBatsman=items2[position];
                        SendButtonQuestion.setTextColor(Color.BLACK);
                        SendButtonQuestion.setEnabled(true);
                        break;
                    case 5:
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

                Log.d("Username:",mUsername);
                Log.d("favourite player",favouriteBatsman);
                Log.d("favourite nota",favouriteTeam);
                Log.d("house",house);
                favouriteBatsman=FavPlayer.getText().toString();

                House house1 = new House(mUsername,favouriteBatsman,favouriteTeam,house);
                mHouseDatabaseReference.push().setValue(house1);
                
                Intent i = new Intent(getActivity(),OptionsActivity.class);
                Bundle b=new Bundle();
                b.putStringArray("users",array);
                i.putExtra("batch",house);
                i.putExtra("username",mUsername);
                i.putExtras(b);
                getActivity().finish();
                startActivity(i);


            }
        });
        mHouseDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("here ->"+dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });


        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();


                if(user!=null){
                    //user is signed in
                    
                    onSignedInInitialize(user.getDisplayName());
                    mUsername=user.getDisplayName();

                    Username.setText("Hi!\n "+user.getDisplayName().toUpperCase()+"\n\nWelcome to NPL 2017!");
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
    private void collectPhoneNumbers(Map<String,Object> users) {

        ArrayList<Long> phoneNumbers = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()){

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            phoneNumbers.add((Long) singleUser.get("username"));
        }

        Log.d("aaa",phoneNumbers.toString());
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

    private void  onSignedInInitialize(String username){
        //mUsername = username;
        attachDatabaseReadListener();

    }
    private void  onSignedOutInitialize(){
       // mUsername = ANONYMOUS;

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
            mHouseDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }
    private void detachDatabaseReadListener(){
        if(mChildEventListener!=null)
            mHouseDatabaseReference.removeEventListener(mChildEventListener);
        mChildEventListener=null;
    }
}
