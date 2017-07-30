package myApp.hamza.npl;

/**
 * Created by Hamza Ahmed on 14-Jul-17.
 */
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.List;



public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";
    private TextView checkInternet;
    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    public static final int RC_SIGN_IN =1;
    private ListView mMessageListView;
    private TextView name;
    private MessageAdapter mMessageAdapter;
    private ProgressBar mProgressBar;
    private ImageButton mPhotoPickerButton;
    private EditText mMessageEditText;
    private Button mSendButton;
    public String NAME;
    private static final int RC_PHOTO_PICKER=2;
    private static final int RC_PHOTO_PICKERStories=3;
    private String mUsername;
    private ImageView Button;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessageDatabaseReference;
    private DatabaseReference mStoriesDatabaseReference;
    private ChildEventListener mChildEventListener;
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
    MediaPlayer mp;


    @Override
    protected void onCreate( final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        recyclerView = (RecyclerView) findViewById(R.id.imagegallery);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        photoPickerButtonStories=(ImageButton)findViewById(R.id.photoPickerButtonStories);
        NAME=ANONYMOUS;
        name = (TextView)findViewById(R.id.Uname);
        Typeface font = Typeface.createFromAsset(getAssets(), "Aller_Rg.ttf");
        name.setTypeface(font);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mMessageListView = (ListView) findViewById(R.id.messageListView);
        mPhotoPickerButton = (ImageButton) findViewById(R.id.photoPickerButton);
        mMessageEditText = (EditText) findViewById(R.id.messageEditText);
        mSendButton = (Button) findViewById(R.id.sendButton);
        mUsername = ANONYMOUS;
        checkInternet=(TextView)findViewById(R.id.checkInternet);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("messages");
        mStoriesDatabaseReference =mFirebaseDatabase.getReference().child("stories");
        mChatPhotoStorageReference =firebaseStorage.getReference().child("chat_photos");
        mStoriesStorageReference =firebaseStorage.getReference().child("stories_pictures");
        Button =(ImageView) findViewById(R.id.backButton);

        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this,OptionsActivity.class);
                startActivity(i);
                finish();
            }
        });

        // Initialize references to views

        Query mHouseDatabaseReference2 =mFirebaseDatabase.getReference().child("stories");

        mHouseDatabaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        Image image1 = new Image();
                        image1.setImage_ID(issue.child("image_ID").getValue().toString());
                        images.add(0,image1);
                        }
                }

                adapter.notifyDataSetChanged();
                adapter.notifyItemInserted(0);
                adapter = new ImagesAdapter(ProfileActivity.this, getmMatch());
                recyclerView.setAdapter(adapter);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        if (mPhotoPickerButton != null)
            mPhotoPickerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: Fire an intent to show an image picker
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                    startActivityForResult(intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);

                }
            });

        if (photoPickerButtonStories != null)
            photoPickerButtonStories.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: Fire an intent to show an image picker
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                    startActivityForResult(intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKERStories);

                }
            });

        if (mMessageEditText != null) {
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
        if (mSendButton != null)
            mSendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FriendlyMessage friendlyMessage = new FriendlyMessage(mMessageEditText.getText().toString(), mUsername, null);
                    mMessageDatabaseReference.push().setValue(friendlyMessage);
                    mMessageEditText.setText("");
                }
            });


        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //user is signed in
                    onSignedInInitialize(user.getDisplayName());
                    NAME = user.getDisplayName();

                    if (!NAME.equals("K142805 Hamza Ahmed")) {
                        photoPickerButtonStories.setVisibility(View.GONE);
                    }

                    name.setText(user.getDisplayName());
                    final List<FriendlyMessage> friendlyMessages = new ArrayList<>();
                    mMessageAdapter = new MessageAdapter(ProfileActivity.this, R.layout.item_message, friendlyMessages, NAME);

                    if (mMessageListView != null)
                        mMessageListView.setAdapter(mMessageAdapter);

                    images = new ArrayList<>();

                    adapter = new ImagesAdapter(ProfileActivity.this, images);

                    if (recyclerView != null)
                        recyclerView.setAdapter(adapter);
                } else {
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
            }

            ;
        };

    }
    public ArrayList<Image> getmMatch(){

        return images;
    }


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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
            } else if (resultCode == RESULT_CANCELED) {
            }
        }
        else if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            StorageReference photoRef =
                    mChatPhotoStorageReference.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener
                    (this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadURL = taskSnapshot.getDownloadUrl();
                            FriendlyMessage friendlyMessage = new FriendlyMessage(null, mUsername, downloadURL.toString());
                            mMessageDatabaseReference.push().setValue(friendlyMessage);



                        }
                    });
        }
        else if (requestCode == RC_PHOTO_PICKERStories && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            StorageReference photoRef =
                    mStoriesStorageReference.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener
                    (this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadURL = taskSnapshot.getDownloadUrl();
                            Image image = new Image(null, downloadURL.toString());
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
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    mMessageAdapter.add(friendlyMessage);
                    mProgressBar.setVisibility(View.GONE);
                    checkInternet.setVisibility(View.GONE);

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                   // FriendlyMessage f =dataSnapshot.getValue(FriendlyMessage.class);

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

