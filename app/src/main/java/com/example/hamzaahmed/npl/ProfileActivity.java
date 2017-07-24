package com.example.hamzaahmed.npl;

/**
 * Created by Hamza Ahmed on 14-Jul-17.
 */
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
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

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";

    public static final String ANONYMOUS = "anonymous";
    private Query mHouseDatabaseReference2;

    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;

    public static final int RC_SIGN_IN =1;

    private ListView mMessageListView;
    private ListView mUserListView;
    private LinearLayout l1;
    private TextView mTextView;
    private TextView name;

    private MessageAdapter mMessageAdapter;
    List notes = new ArrayList<>();
    private String currentDateTimeString;
    private TextView date1;
    private ImageView dp2;
    private String url2;
    private ProgressBar mProgressBar;

    private ImageButton mPhotoPickerButton;

    private EditText mMessageEditText;

    private Button mSendButton;
    private ImageView profile;
    public String NAME;

    private static final int RC_PHOTO_PICKER =  2;
    private static final int RC_PHOTO_PICKERStories=3;

    private String mUsername;
    private ImageView Button;
    private String time;

    private FirebaseDatabase mFirebaseDatabase;

    private DatabaseReference mMessageDatabaseReference;
    private DatabaseReference mStoriesDatabaseReference;

    private DatabaseReference mUsersDatabaseReference;

    private ChildEventListener mChildEventListener;
    private ChildEventListener mChildEventListener2;

    private FirebaseAuth mFirebaseAuth;
    private ImagesAdapter adapter;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private ImageButton photoPickerButtonStories;
    private FirebaseStorage firebaseStorage;
    FirebaseUser user;
    private StorageReference mChatPhotoStorageReference;
    private StorageReference mStoriesStorageReference;
    private String side2;
    private RecyclerView recyclerView;
    private ArrayList<Image> images;
    @Override
    protected void onCreate( final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        recyclerView = (RecyclerView) findViewById(R.id.imagegallery);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        //l1=(LinearLayout)findViewById(R.id.rightLayout);
        photoPickerButtonStories=(ImageButton)findViewById(R.id.photoPickerButtonStories);
        NAME=ANONYMOUS;
        Button =(ImageView) findViewById(R.id.backButton);
        mTextView = (TextView)findViewById(R.id.messageTextView);
        name = (TextView)findViewById(R.id.Uname);
        Typeface font = Typeface.createFromAsset(getAssets(), "Aller_Rg.ttf");

        name.setTypeface(font);
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this,OptionsActivity.class);
                startActivity(i);
                finish();
            }
        });

        mUsername = ANONYMOUS;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("messages");
        mStoriesDatabaseReference =mFirebaseDatabase.getReference().child("stories");
        mChatPhotoStorageReference =firebaseStorage.getReference().child("chat_photos");
        mStoriesStorageReference =firebaseStorage.getReference().child("stories_pictures");
        mUsersDatabaseReference = mFirebaseDatabase.getReference().child("users");
        Log.d("oncreate ",mMessageDatabaseReference.getDatabase().toString());



        // Initialize references to views
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mMessageListView = (ListView) findViewById(R.id.messageListView);
        mPhotoPickerButton = (ImageButton) findViewById(R.id.photoPickerButton);
        mMessageEditText = (EditText) findViewById(R.id.messageEditText);
        mSendButton = (Button) findViewById(R.id.sendButton);

        Query mHouseDatabaseReference2 =mFirebaseDatabase.getReference().child("stories");

        mHouseDatabaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println(issue.child("image_ID").getValue());
                        Image image1 = new Image();
                        System.out.println("id: "+image1.getId());

                        image1.setImage_ID(issue.child("image_ID").getValue().toString());
                        images.add(image1);

                       /* Match match = new Match();
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
*/
                    }

                    adapter.notifyDataSetChanged();
                    adapter = new ImagesAdapter(ProfileActivity.this,getmMatch());
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


        // Initialize message ListView and its adapter

        // Initialize progress bar
        if(mProgressBar!=null)
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);

        // ImagePickerButton shows an image picker to upload a image for a message
        if(mPhotoPickerButton!=null)
        mPhotoPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Fire an intent to show an image picker
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(intent.createChooser(intent,"Complete action using"),RC_PHOTO_PICKER);

            }
        });
        if(photoPickerButtonStories!=null)
            photoPickerButtonStories.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: Fire an intent to show an image picker
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/jpeg");
                    intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                    startActivityForResult(intent.createChooser(intent,"Complete action using"),RC_PHOTO_PICKERStories);

                }
            });

        // Enable Send button when there's text to send
        if(mMessageEditText!=null) {
            mMessageEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.toString().trim().length() > 0) {
                        mSendButton.setEnabled(true);
                    } else {
                        mSendButton.setEnabled(false);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }

            });
            mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        }
        // Send button sends a message and clears the EditText
        if(mSendButton!=null)
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Send messages on click
                currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                time=""+currentDateTimeString.substring(12,16)+" "+currentDateTimeString.substring(20,24).toUpperCase();
                Log.d("saf123",time);
                //System.out.println("side: "+side2);
                FriendlyMessage friendlyMessage = new FriendlyMessage(mMessageEditText.getText().toString(), mUsername, null,time,side2);
                // Clear input box
                mMessageDatabaseReference.push().setValue(friendlyMessage);
                mMessageEditText.setText("");


            }
        });


        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    //user is signed in
                    onSignedInInitialize(user.getDisplayName());
                    NAME =user.getDisplayName();
                    name.setText(user.getDisplayName());
                    final List<FriendlyMessage> friendlyMessages = new ArrayList<>();
                    mMessageAdapter = new MessageAdapter(ProfileActivity.this, R.layout.item_message, friendlyMessages,NAME,notes);
                    if(mMessageListView!=null)
                        mMessageListView.setAdapter(mMessageAdapter);
                    images = new ArrayList<>();
                    adapter = new ImagesAdapter(ProfileActivity.this,images);
                    if(recyclerView!=null)
                        recyclerView.setAdapter(adapter);

                    Log.d("hamza here","this");
                    Log.d("check",NAME);

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
                                            AuthUI.EMAIL_PROVIDER,
                                            AuthUI.GOOGLE_PROVIDER
                                    ).build(),
                            RC_SIGN_IN);




                }
            };
        };
        mHouseDatabaseReference2 =mFirebaseDatabase.getReference().child("messages").orderByChild("names");
        mHouseDatabaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        //System.out.println(user.getDisplayName().equals("K142805 Hamza Ahmed")+" "+issue.child("name").getValue().equals("K142805 Hamza Ahmed"));

                        NAME =mFirebaseAuth.getCurrentUser().getDisplayName();
                        //System.out.println("name"+issue.child("name").getValue());
                        System.out.println(issue.child("name").getValue());
                        if(NAME.equals(issue.child("name").getValue())){
                            //System.out.println("in the box");
                             side2="RIGHT";
                            }
                        else{
                            side2="LEFT";
                        }

  /*                          messageTextView.setTextColor((Color.parseColor("#800000")));
                            params.weight=1.0f;
                            params.gravity=Gravity.START;
                            l1.setLayoutParams(params);


*/





                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public ArrayList<Image> getmMatch(){

        return images;
    }

   /* private ArrayList<Image> prepareData(){

        ArrayList<Image> theimage = new ArrayList<>();
        for(int i = 0; i< image_ids.length; i++){
            Image image = new Image();
            image.setImage_ID(image_ids[i]);
            theimage.add(image);
        }
        return theimage;
    }
*/
 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sign_out_menu:
                AuthUI.getInstance().signOut(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    @Override
    protected void onPause(){
        super.onPause();
        if(mAuthStateListner!=null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }
        detachDatabaseReadListener();
        mMessageAdapter.clear();
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==RC_SIGN_IN){
            if(resultCode ==RESULT_OK){
                Toast.makeText(this,"Signed in!",Toast.LENGTH_SHORT).show();
            }
            else if(resultCode == RESULT_CANCELED){
                Toast.makeText(this,"Sign in cancelled",Toast.LENGTH_SHORT).show();

            }





            //upload file to firebase storage
        }
        else if(requestCode == RC_PHOTO_PICKER && resultCode==RESULT_OK){
            Uri selectedImageUri= data.getData();
            StorageReference photoRef =
                    mChatPhotoStorageReference.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener
                    (this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadURL =taskSnapshot.getDownloadUrl();
                            FriendlyMessage friendlyMessage = new FriendlyMessage(null,mUsername,downloadURL.toString(),time,side2);
                            //System.out.println(side2);
                            mMessageDatabaseReference.push().setValue(friendlyMessage);

                        }
                    });
        }
        else if(requestCode == RC_PHOTO_PICKERStories && resultCode==RESULT_OK){
            Uri selectedImageUri= data.getData();
            StorageReference photoRef =
                    mStoriesStorageReference.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener
                    (this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadURL =taskSnapshot.getDownloadUrl();
                            Image image = new Image(null,downloadURL.toString());
                            //System.out.println(side2);
                            mStoriesDatabaseReference.push().setValue(image);
                        }
                    });
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }

    private void  onSignedInInitialize(String username){
        mUsername = username;
        attachDatabaseReadListener();

    }
    private void  onSignedOutInitialize(){
        mUsername = ANONYMOUS;
        mMessageAdapter.clear();

        detachDatabaseReadListener();
    }
    private void attachDatabaseReadListener(){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    FriendlyMessage friendlyMessage = dataSnapshot.getValue(FriendlyMessage.class);
                    String splited = friendlyMessage.getName();
                    String name=friendlyMessage.getName();
                    Image image =dataSnapshot.getValue(Image.class);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    mMessageAdapter.add(friendlyMessage);

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
            mMessageDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                       // FriendlyMessage note = noteDataSnapshot.getValue(FriendlyMessage.class);
                        //Log.d("message ",""+note.getPhotoUrl());

                      //  notes.add(note);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    FriendlyMessage friendlyMessage = dataSnapshot.getValue(FriendlyMessage.class);
                    String splited = friendlyMessage.getName();
                    String name=friendlyMessage.getName();
                    Image image =dataSnapshot.getValue(Image.class);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    mMessageAdapter.add(friendlyMessage);

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
            mMessageDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                        // FriendlyMessage note = noteDataSnapshot.getValue(FriendlyMessage.class);
                        //Log.d("message ",""+note.getPhotoUrl());

                        //  notes.add(note);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
    private void detachDatabaseReadListener(){
        if(mChildEventListener!=null)
            mMessageDatabaseReference.removeEventListener(mChildEventListener);
        mChildEventListener=null;
    }


}

