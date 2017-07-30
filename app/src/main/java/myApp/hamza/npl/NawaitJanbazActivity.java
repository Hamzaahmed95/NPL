package myApp.hamza.npl;

/**
 * Created by Hamza Ahmed on 14-Jul-17.
 */
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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


public class NawaitJanbazActivity extends AppCompatActivity {

    private static final String TAG = "NawaitJanbazActivity";
    public static final String ANONYMOUS = "anonymous";
    public static final int RC_SIGN_IN =1;
    private TextView name;
    private ImageButton mPhotoPickerButton;
    private EditText mMessageEditText;
    private Button mSendButton;
    public String NAME;
    private static final int RC_PHOTO_PICKERStories=3;
    private String mUsername;
    private ImageView Button;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mStoriesDatabaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseAuth mFirebaseAuth;
    private TeamAdapter adapter;
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
        setContentView(R.layout.nawait_janbax);

        recyclerView = (RecyclerView) findViewById(R.id.nawaitJanbaz);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        photoPickerButtonStories=(ImageButton)findViewById(R.id.photoPickerButtonStories);
        NAME=ANONYMOUS;
        images = new ArrayList<>();
        mUsername = ANONYMOUS;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        mStoriesDatabaseReference =mFirebaseDatabase.getReference().child("janbaz");
        mStoriesStorageReference =firebaseStorage.getReference().child("janbaz_team");
        Button =(ImageView) findViewById(R.id.backButton);

        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NawaitJanbazActivity.this,Teams.class);
                startActivity(i);
                finish();
            }
        });

        // Initialize references to views

        Query mHouseDatabaseReference2 =mFirebaseDatabase.getReference().child("janbaz");

        mHouseDatabaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        Image image1 = new Image();
                        image1.setImage_ID(issue.child("image_ID").getValue().toString());
                        images.add(image1);
                    }
                }

                //adapter.notifyDataSetChanged();
                adapter = new TeamAdapter(NawaitJanbazActivity.this, getmMatch());
                recyclerView.setAdapter(adapter);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

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




                    adapter = new TeamAdapter(NawaitJanbazActivity.this, images);

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
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
            } else if (resultCode == RESULT_CANCELED) {
            }
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
        detachDatabaseReadListener();
    }
    private void attachDatabaseReadListener(){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

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
            mStoriesDatabaseReference.addChildEventListener(mChildEventListener);
            mStoriesDatabaseReference.addValueEventListener(new ValueEventListener() {
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
            mStoriesDatabaseReference.removeEventListener(mChildEventListener);
        mChildEventListener=null;
    }


}

