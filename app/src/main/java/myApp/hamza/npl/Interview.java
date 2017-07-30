package myApp.hamza.npl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
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
 * Created by Hamza Ahmed on 19-Jul-17.
 */

public class Interview extends AppCompatActivity {

    private static final String TAG = "Interview";

    public static final String ANONYMOUS = "anonymous";

    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;

    public static final int RC_SIGN_IN =1;

    private ListView mmessageListViewInterview;


    private ImageView closeButton;

    private InterviewAdapter mInterviewAdapter;

    private ProgressBar mProgressBar;

    private ImageButton mphotoPickerButtonInterview;

    private EditText mmessageEditTextInterview;

    private android.widget.Button mSendButton;
    public String NAME;

    private static final int REQUEST_TAKE_GALLERY_VIDEO =  2;

    private String mUsername;
    private ImageView Button;
    private List<String> notes;
    private FirebaseDatabase mFirebaseDatabase;

    private DatabaseReference mMessageDatabaseReference;

    private ChildEventListener mChildEventListener;

    private FirebaseAuth mFirebaseAuth;

    private FirebaseAuth.AuthStateListener mAuthStateListner;

    private FirebaseStorage firebaseStorage;

    private StorageReference mChatPhotoStorageReference;
    private String name;
    private String runs;

    ProgressBar mprogressBar;


    @Override
    protected void onCreate( final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.interview);

        NAME=ANONYMOUS;

        notes = new ArrayList<String>();
        mUsername = ANONYMOUS;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        closeButton = (ImageView) findViewById(R.id.backButtonInterview);

        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("Interview");
        mChatPhotoStorageReference =firebaseStorage.getReference().child("Interview_photos");
        Log.d("oncreate ",mMessageDatabaseReference.getDatabase().toString());



        // Initialize references to views
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mmessageListViewInterview = (ListView) findViewById(R.id.messageListViewInterview);
        mphotoPickerButtonInterview = (ImageButton) findViewById(R.id.photoPickerButtonInterview);
        mmessageEditTextInterview = (EditText) findViewById(R.id.messageEditTextInterview);
        mSendButton = (Button) findViewById(R.id.sendButtonInterview);

        // Initialize message ListView and its adapter

        // Initialize progress bar
        if(mProgressBar!=null)
            mProgressBar.setVisibility(ProgressBar.INVISIBLE);

        // ImagePickerButton shows an image picker to upload a image for a message
        if(mphotoPickerButtonInterview!=null)
            mphotoPickerButtonInterview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: Fire an intent to show an image picker
                    // intent = new Intent(Intent.ACTION_GET_CONTENT);
                    Intent myIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    myIntent.setType("*/*");
                    startActivityForResult(Intent.createChooser(myIntent,"Select File:-"),REQUEST_TAKE_GALLERY_VIDEO);


                }
            });

        // Enable Send button when there's text to send
        if(mmessageEditTextInterview!=null) {
            mmessageEditTextInterview.addTextChangedListener(new TextWatcher() {
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
            mmessageEditTextInterview.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        }
        mmessageListViewInterview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("pos"+i);
            }
        });
        // Send button sends a message and clears the EditText
        if(mSendButton!=null)
            mSendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String score = mmessageEditTextInterview.getText().toString();
                    String [] arr = score.split("-");
                    name=arr[0];
                    runs=arr[1];

                    // TODO: Send messages on click
                    InterviewClass InterviewClass = new InterviewClass(name,runs,null);

                    // Clear input box
                    mMessageDatabaseReference.push().setValue(InterviewClass);
                    mmessageEditTextInterview.setText("");


                }
            });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Interview.this,OptionsActivity.class);
                startActivity(i);
                finish();
            }
        });
        Bundle extra =getIntent().getExtras();
        if(extra!=null) {
            String url2 = extra.getString("username");
            Log.d("hamza: ",url2);
            if(!url2.equals("K142805 Hamza Ahmed")){
                Log.d(TAG, ""+url2);
                mSendButton.setVisibility(View.GONE);
                mphotoPickerButtonInterview.setVisibility(View.GONE);
                mmessageEditTextInterview.setVisibility(View.GONE);
            }
        }


        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    //user is signed in
                    onSignedInInitialize(user.getDisplayName());
                    final List<InterviewClass> InterviewClasses = new ArrayList<>();
                    mInterviewAdapter = new InterviewAdapter(Interview.this, R.layout.item_interview, InterviewClasses);
                    if(mmessageListViewInterview!=null)
                        mmessageListViewInterview.setAdapter(mInterviewAdapter);




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


    }


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
        mInterviewAdapter.clear();
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
        else if(requestCode == REQUEST_TAKE_GALLERY_VIDEO && resultCode==RESULT_OK){
            Uri selectedImageUri= data.getData();
            StorageReference photoRef =
                    mChatPhotoStorageReference.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener
                    (this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadURL =taskSnapshot.getDownloadUrl();
                            InterviewClass InterviewClass = new InterviewClass(null,null,downloadURL.toString());
                            mMessageDatabaseReference.push().setValue(InterviewClass);
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
        mInterviewAdapter.clear();

        detachDatabaseReadListener();
    }
    private void attachDatabaseReadListener(){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    InterviewClass InterviewClass = dataSnapshot.getValue(InterviewClass.class);
                    if(InterviewClass!=null)
                        mInterviewAdapter.add(InterviewClass);

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    InterviewClass f =dataSnapshot.getValue(InterviewClass.class);

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

                        InterviewClass Interview =noteDataSnapshot.getValue(InterviewClass.class);
                        String pictures[] = new String[100];
                        //  Log.d("Interview ",""+Interview.getPICTURE());

                        notes.add(Interview.getPICTURE());
                    }
                    for (String s:notes){
                        Log.d("hamza1234",s);

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