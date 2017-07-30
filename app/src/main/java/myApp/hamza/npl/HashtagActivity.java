package myApp.hamza.npl;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hamza Ahmed on 26-Jul-17.
 */

public class HashtagActivity extends AppCompatActivity {

    private static final String TAG = "HashtagActivity";
    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    public static final int RC_SIGN_IN =1;
    private ListView mMessageListView;
    private TextView name;
    private HashtagAdapter mHashtagAdapter;
    private ProgressBar mProgressBar;
    private ImageButton mPhotoPickerButton;
    private EditText mMessageEditText;
    private android.widget.Button mSendButton;
    public String NAME;
    private static final int RC_PHOTO_PICKER=2;
    private String mUsername;
    private ImageView Button;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessageDatabaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private FirebaseStorage firebaseStorage;
    FirebaseUser user;
    private StorageReference mChatPhotoStorageReference;

    @Override
    protected void onCreate( final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.hashtag_activity);
        NAME=ANONYMOUS;
        name = (TextView)findViewById(R.id.UnameHashtag);
        Typeface font = Typeface.createFromAsset(getAssets(), "Aller_Rg.ttf");
        name.setTypeface(font);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBarHashtag);
        mMessageListView = (ListView) findViewById(R.id.messageListViewHashtag);
        mPhotoPickerButton = (ImageButton) findViewById(R.id.photoPickerButtonHashtag);
        mMessageEditText = (EditText) findViewById(R.id.messageEditTextHashtag);
        mSendButton = (Button) findViewById(R.id.sendButtonHashtag);
        mUsername = ANONYMOUS;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("hashtag_messages");
        mChatPhotoStorageReference =firebaseStorage.getReference().child("hashtag_photos");
        Button =(ImageView) findViewById(R.id.backButtonHashtag);

        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HashtagActivity.this,OptionsActivity.class);
                startActivity(i);
                finish();
            }
        });

        // Initialize references to views



        if (mPhotoPickerButton != null)
            mPhotoPickerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                    startActivityForResult(intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);

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
                    FriendlyHashtag friendlyMessage = new FriendlyHashtag(mMessageEditText.getText().toString(), mUsername, null);

                    Toast.makeText(HashtagActivity.this,"Your Message has been received! ",Toast.LENGTH_SHORT).show();
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
                    }

                    name.setText(user.getDisplayName());
                    final List<FriendlyHashtag> friendlyMessages = new ArrayList<>();
                    mHashtagAdapter = new HashtagAdapter(HashtagActivity.this, R.layout.item_message, friendlyMessages, NAME);

                    if (mMessageListView != null)
                        mMessageListView.setAdapter(mHashtagAdapter);



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


    @Override
    protected void onPause(){
        super.onPause();
        if(mAuthStateListner!=null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }
        detachDatabaseReadListener();
        mHashtagAdapter.clear();
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
                            FriendlyHashtag friendlyMessage = new FriendlyHashtag(null, mUsername, downloadURL.toString());
                            mMessageDatabaseReference.push().setValue(friendlyMessage);



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
        mHashtagAdapter.clear();

        detachDatabaseReadListener();
    }
    private void attachDatabaseReadListener(){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    FriendlyHashtag friendlyMessage = dataSnapshot.getValue(FriendlyHashtag.class);
                    mHashtagAdapter.add(friendlyMessage);
                    mProgressBar.setVisibility(View.GONE);

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    // FriendlyHashtag f =dataSnapshot.getValue(FriendlyHashtag.class);

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

