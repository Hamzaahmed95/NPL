package com.example.hamzaahmed.npl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnSuccessListener;
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
import com.google.firebase.storage.UploadTask;

import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static com.example.hamzaahmed.npl.ProfileActivity.ANONYMOUS;
import static com.example.hamzaahmed.npl.ProfileActivity.DEFAULT_MSG_LENGTH_LIMIT;

/**
 * Created by Hamza Ahmed on 13-Jul-17.
 */

public class PollingFragment extends Fragment {

    private FirebaseStorage firebaseStorage;
    private static final int RC_PHOTO_PICKER = 2;
    private static final int RC_PHOTO_PICKER2 = 3;
    private static final int RC_PHOTO_PICKER3 = 4;
    private static final int RC_PHOTO_PICKER4 = 5;
    private StorageReference TeamPlayingStorageReference;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mPollDatabaseReference;
    private DatabaseReference mPollDatabaseReference2;
    private DatabaseReference mPollDatabaseReference3;
    private DatabaseReference mPollDatabaseReference4;
    private DatabaseReference mPollDatabaseReference5;
    private DatabaseReference mPollDatabaseReference6;
    public static final int RC_SIGN_IN =1;
    private ChildEventListener mChildEventListener;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private TextView username;
    private TextView pollQuestion;
    String mUsername;
    private EditText pollAskQuestion;
    private EditText PollAns1;
    private EditText PollAns2;
    private ImageButton mPhotoPickerButton1;
    private ImageButton mPhotoPickerButton2;
    private ImageButton mPhotoPickerButton3;
    private ImageButton mPhotoPickerButton4;
    private ImageView pollTeam1;
    private ImageView pollTeam2;
    private ImageView pollTeam3;
    private ImageView pollTeam4;
    private Button sendButton;
    private Button sendButton2;
    private String url1;
    private String url2;
    private String username1;
    private String url3;
    private String url4;
    private String questionS;
    private String ans1;
    private String ans2;
    private Boolean truel=true;
    private int count1;
    private int count2;
    private Boolean true2=true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.polls, container, false);
        firebaseStorage = FirebaseStorage.getInstance();
        TeamPlayingStorageReference =firebaseStorage.getReference().child("polls");
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mPollDatabaseReference = mFirebaseDatabase.getReference().child("poll1");
        mPollDatabaseReference2 = mFirebaseDatabase.getReference().child("poll2");
        mPollDatabaseReference3 = mFirebaseDatabase.getReference().child("poll3");
        mPollDatabaseReference4 = mFirebaseDatabase.getReference().child("poll4");
        mPollDatabaseReference5 = mFirebaseDatabase.getReference().child("poll5");
        mPollDatabaseReference6 = mFirebaseDatabase.getReference().child("poll6");
        username= (TextView)view.findViewById(R.id.Username);
        pollQuestion = (TextView)view.findViewById(R.id.pollQuestion);
        pollAskQuestion=(EditText)view.findViewById(R.id.pollAskQuestion);
        PollAns1=(EditText)view.findViewById(R.id.pollAnswer1);
        count1=0;
        count2=0;
        PollAns2=(EditText)view.findViewById(R.id.pollAnswer2);
        mPhotoPickerButton1=(ImageButton)view.findViewById(R.id.photoPickerPoll1);
        mPhotoPickerButton2=(ImageButton)view.findViewById(R.id.photoPickerPoll2);
        mPhotoPickerButton3=(ImageButton)view.findViewById(R.id.photoPickerPoll3);
        mPhotoPickerButton4=(ImageButton)view.findViewById(R.id.photoPickerPoll4);
        pollTeam1=(ImageView)view.findViewById(R.id.pollTeam1);
        pollTeam2=(ImageView)view.findViewById(R.id.pollTeam2);
        pollTeam3=(ImageView)view.findViewById(R.id.pollTeam3);
        pollTeam4=(ImageView)view.findViewById(R.id.pollTeam4);
        sendButton=(Button)view.findViewById(R.id.sendButtonPolls);
        sendButton2=(Button)view.findViewById(R.id.sendButtonPolls2);
        username1=ANONYMOUS;

/*
        Query mHouseDatabaseReference1 =mFirebaseDatabase.getReference().child("poll1").limitToLast(1);;

        mHouseDatabaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                       // System.out.println("hamza here"+issue.getValue());
                        if(issue.child("questionSet").getValue().toString()!=null)
                            questionS=issue.child("url1").getValue().toString();
                        pollQuestion.setText(questionS);

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

            */

        Query mHouseDatabaseReference1 =mFirebaseDatabase.getReference().child("poll1").limitToLast(1);;

        mHouseDatabaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        if(issue.child("questionSet").getValue().toString()!=null){
                            questionS=issue.child("questionSet").getValue().toString();
                            pollQuestion.setText(questionS);
                        }


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

        Query mHouseDatabaseReference3 =mFirebaseDatabase.getReference().child("poll3").limitToLast(1);;

        mHouseDatabaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hamza here"+issue.getValue());
                        if(issue.child("url1").getValue().toString()!=null)
                            url1=issue.child("url1").getValue().toString();
                        Glide.with(pollTeam1.getContext())
                                .load(url1)
                                .into(pollTeam1);
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
        Query mHouseDatabaseReference4 =mFirebaseDatabase.getReference().child("poll4").limitToLast(1);;

        mHouseDatabaseReference4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hamza here"+issue.getValue());
                        if(issue.child("url2").getValue().toString()!=null)
                            url2=issue.child("url2").getValue().toString();
                        Glide.with(pollTeam2.getContext())
                                .load(url2)
                                .into(pollTeam2);
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
        Query mHouseDatabaseReference5 =mFirebaseDatabase.getReference().child("poll5").limitToLast(1);;

        mHouseDatabaseReference5.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hamza here"+issue.getValue());
                        if(issue.child("url3").getValue().toString()!=null)
                            url3=issue.child("url3").getValue().toString();
                        Glide.with(pollTeam3.getContext())
                                .load(url3)
                                .into(pollTeam3);
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
        Query mHouseDatabaseReference6 =mFirebaseDatabase.getReference().child("poll6").limitToLast(1);;

        mHouseDatabaseReference6.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hamza here"+issue.getValue());
                        if(issue.child("url4").getValue().toString()!=null)
                            url4=issue.child("url4").getValue().toString();
                        Glide.with(pollTeam4.getContext())
                                .load(url4)
                                .into(pollTeam4);
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
        if(mPhotoPickerButton3!=null)
            mPhotoPickerButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: Fire an intent to show an image picker
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    // intent.setType("image/png");
                    intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                    startActivityForResult(intent.createChooser(intent,"Complete action using"),RC_PHOTO_PICKER3);

                }
            });  if(mPhotoPickerButton4!=null)
            mPhotoPickerButton4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: Fire an intent to show an image picker
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    // intent.setType("image/png");
                    intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                    startActivityForResult(intent.createChooser(intent,"Complete action using"),RC_PHOTO_PICKER4);

                }
            });

        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //user is signed in
                    onSignedInInitialize(user.getDisplayName());
                    username1=user.getDisplayName();
                    if(!username1.equals("K142805 Hamza Ahmed")) {
                    pollAskQuestion.setVisibility(View.GONE);
                        sendButton2.setVisibility(View.GONE);
                        mPhotoPickerButton1.setVisibility(View.GONE);
                        mPhotoPickerButton2.setVisibility(View.GONE);
                        mPhotoPickerButton3.setVisibility(View.GONE);
                        mPhotoPickerButton4.setVisibility(View.GONE);
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
        sendButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question =pollAskQuestion.getText().toString();
                Poll poll = new Poll(username1,question,null,null,null,null,null,null);
                mPollDatabaseReference.push().setValue(poll);

            }
        });




            if (PollAns1 != null ) {
                PollAns1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (charSequence.toString().trim().length() > 0) {
                            count1=2;
                        } else {
                            count1=1;
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                    }

                });
                //mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
            }
            if (PollAns2 != null) {
                PollAns2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (charSequence.toString().trim().length() > 0) {
                            count2=2;
                        } else {
                            count2=1;
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                    }

                });
                //mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

        }


       /* if(count1==2 && !true2){
            sendButton.setEnabled(false);
        }
        else{
            sendButton.setEnabled(true);
        }
*/
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("count1= "+count1);
                System.out.println("count2= "+count2);
                if(count1==2 && count2==2) {
                    String ans1 = PollAns1.getText().toString();
                    String ans2 = PollAns2.getText().toString();
                    Poll poll = new Poll(username1, null, ans1, ans2, null, null, null, null);
                    mPollDatabaseReference2.push().setValue(poll);
                    Intent i = new Intent(getActivity(), OptionsActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getContext(),"please fill the answers",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == RC_PHOTO_PICKER && resultCode==RESULT_OK){
            Uri selectedImageUri= data.getData();
            StorageReference photoRef =
                    TeamPlayingStorageReference.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener
                    (getActivity(), new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadURL =taskSnapshot.getDownloadUrl();
                            Poll poll = new Poll(null,null,null,null,downloadURL.toString(),null,null,null);
                            //Log.d("Musername","here-> "+pointTablePicture.getName().substring(7));

                            boolean isPhoto = downloadURL.toString() != null;
                            if (isPhoto) {
                                Toast.makeText(getActivity(), "Loading your picture!!", Toast.LENGTH_SHORT).show();
                                pollTeam1.setVisibility(View.VISIBLE);
                                Glide.with(pollTeam1.getContext())
                                        .load(poll.getUrl1())
                                        .into(pollTeam1);
                                mPollDatabaseReference3.push().setValue(poll);
                            }
                        }
                    });
        }
        else  if(requestCode == RC_PHOTO_PICKER2 && resultCode==RESULT_OK){
            Uri selectedImageUri= data.getData();
            StorageReference photoRef =
                    TeamPlayingStorageReference.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener
                    (getActivity(), new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadURL =taskSnapshot.getDownloadUrl();
                            Poll poll = new Poll(null,null,null,null,null,downloadURL.toString(),null,null);
                            //Log.d("Musername","here-> "+pointTablePicture.getName().substring(7));

                            boolean isPhoto = downloadURL.toString() != null;
                            if (isPhoto) {
                                Toast.makeText(getActivity(), "Loading your picture!!", Toast.LENGTH_SHORT).show();
                                pollTeam2.setVisibility(View.VISIBLE);
                                Glide.with(pollTeam2.getContext())
                                        .load(poll.getUrl2())
                                        .into(pollTeam2);
                                mPollDatabaseReference4.push().setValue(poll);
                            }
                        }
                    });
        }
        else  if(requestCode == RC_PHOTO_PICKER3 && resultCode==RESULT_OK){
            Uri selectedImageUri= data.getData();
            StorageReference photoRef =
                    TeamPlayingStorageReference.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener
                    (getActivity(), new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadURL =taskSnapshot.getDownloadUrl();
                            Poll poll = new Poll(null,null,null,null,null,null,downloadURL.toString(),null);
                            //Log.d("Musername","here-> "+pointTablePicture.getName().substring(7));

                            boolean isPhoto = downloadURL.toString() != null;
                            if (isPhoto) {
                                Toast.makeText(getActivity(), "Loading your picture!!", Toast.LENGTH_SHORT).show();
                                pollTeam3.setVisibility(View.VISIBLE);
                                Glide.with(pollTeam3.getContext())
                                        .load(poll.getUrl3())
                                        .into(pollTeam3);
                                mPollDatabaseReference5.push().setValue(poll);
                            }
                        }
                    });
        }   else  if(requestCode == RC_PHOTO_PICKER4 && resultCode==RESULT_OK){
            Uri selectedImageUri= data.getData();
            StorageReference photoRef =
                    TeamPlayingStorageReference.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener
                    (getActivity(), new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadURL =taskSnapshot.getDownloadUrl();
                            Poll poll = new Poll(null,null,null,null,null,null,null,downloadURL.toString());
                            //Log.d("Musername","here-> "+pointTablePicture.getName().substring(7));

                            boolean isPhoto = downloadURL.toString() != null;
                            if (isPhoto) {
                                Toast.makeText(getActivity(), "Loading your picture!!", Toast.LENGTH_SHORT).show();
                                pollTeam4.setVisibility(View.VISIBLE);
                                Glide.with(pollTeam4.getContext())
                                        .load(poll.getUrl4())
                                        .into(pollTeam4);
                                mPollDatabaseReference6.push().setValue(poll);
                            }
                        }
                    });
        }

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
                    Poll poll = dataSnapshot.getValue(Poll.class);
                   // pollQuestion.setText(poll.getQuestionSet());
                    //   BatsmanBowler batsmanBowler = dataSnapshot.getValue(BatsmanBowler.class);


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
            mPollDatabaseReference.addChildEventListener(mChildEventListener);
            mPollDatabaseReference2.addChildEventListener(mChildEventListener);
            mPollDatabaseReference3.addChildEventListener(mChildEventListener);
            mPollDatabaseReference4.addChildEventListener(mChildEventListener);
            mPollDatabaseReference5.addChildEventListener(mChildEventListener);
            mPollDatabaseReference6.addChildEventListener(mChildEventListener);
        }
    }
    private void detachDatabaseReadListener(){
        if(mChildEventListener!=null)
            mPollDatabaseReference.removeEventListener(mChildEventListener);
        mPollDatabaseReference2.removeEventListener(mChildEventListener);
        mPollDatabaseReference3.addChildEventListener(mChildEventListener);
        mPollDatabaseReference4.addChildEventListener(mChildEventListener);
        mPollDatabaseReference5.addChildEventListener(mChildEventListener);
        mPollDatabaseReference6.addChildEventListener(mChildEventListener);
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
