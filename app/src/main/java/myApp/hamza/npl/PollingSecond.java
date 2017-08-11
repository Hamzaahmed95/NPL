package myApp.hamza.npl;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;
import static myApp.hamza.npl.OptionsFragment.ANONYMOUS;

/**
 * Created by Hamza Ahmed on 18-Jul-17.
 */

public class PollingSecond extends Fragment {



    private FirebaseStorage firebaseStorage;
    private TextView date2;
    public static final String PREFS_NAME1 = "MyAppSharedPrefs";
    private StorageReference PointsTableStorageReference;
    private Button send1;
    private DatabaseReference mMessageDatabaseReference;
    private DatabaseReference mMessageDatabaseReference2;
    private FirebaseDatabase mFirebaseDatabase;
    private EditText date1;
    private ChildEventListener mChildEventListener;
    private ImageButton mPhotoPickerButton;
    private ImageView imageView;
    public static final int RC_SIGN_IN =1;
    private FirebaseAuth mFirebaseAuth;
    private String url;
    private String checking;
    public static final String PREFS_NAME = "MyAppSharedPrefs";
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    String url1;
    private String username1;
    private ImageView backButton5;
    ProgressBar mprogressBar;
    private String mUsername;
    String url2;
    private int count1;
    private static final int RC_PHOTO_PICKER =  2;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.polling, container, false);
        firebaseStorage = FirebaseStorage.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        PointsTableStorageReference =firebaseStorage.getReference().child("polling_picture");
        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("polling_2");
        mMessageDatabaseReference2 =mFirebaseDatabase.getReference().child("polling_1");
        date1 =(EditText)view.findViewById(R.id.date1);
       // date2 =(TextView)view.findViewById(R.id.date2);
        imageView =(ImageView)view.findViewById(R.id.photoImageView2);
        username1 = ProfileActivity.ANONYMOUS;
        mUsername = ANONYMOUS;
        count1 = 0;
        backButton5=(ImageView)view.findViewById(R.id.backButton5);
        backButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),OptionsActivity.class);
                startActivity(i);
            }
        });
        mprogressBar = (ProgressBar) view.findViewById(R.id.progressBarPT);
        mPhotoPickerButton = (ImageButton) view.findViewById(R.id.photoPickerButton2);
        ObjectAnimator anim = ObjectAnimator.ofInt(mprogressBar, "progress", 0, 100);
        anim.setDuration(4000);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();
        send1=(Button)view.findViewById(R.id.send1);
        mFirebaseAuth = FirebaseAuth.getInstance();
        Bundle extra =getActivity().getIntent().getExtras();
        if(extra!=null) {
            url2 = extra.getString("username");
            Log.d("hamza: ",url2);

            if(!url2.equals("K142805 Hamza Ahmed")){
               // send1.setVisibility(View.GONE);
                System.out.println("here!!");
              //  date1.setVisibility(View.GONE);
                mPhotoPickerButton.setVisibility(View.GONE);
            }
        }

        Query mHouseDatabaseReference2 =mFirebaseDatabase.getReference().child("polling2").limitToLast(1);;

        mHouseDatabaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println(issue.child("photoUrl").getValue());
                        //if(issue.child("photoUrl").getValue().toString()!=null)
                        url1=issue.child("photoUrl").getValue().toString();
                        Glide.with(imageView.getContext())
                                .load(url1)
                                .into(imageView);
                        mprogressBar.setVisibility(View.GONE);
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
        Query mHouseDatabaseReference3 =mFirebaseDatabase.getReference().child("polling_2").orderByChild("mUsername");

        mHouseDatabaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"
                        mprogressBar.setVisibility(View.GONE);
                       // if(!issue.child("mUsername").getValue().equals(null)&&!username1.equals(null))
                    //  if(issue.child("mUsername").getValue().equals(mUsername)){
                      // System.out.println("hey");
                        //}
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



        Poll2 Poll21 = new Poll2();







        if(mPhotoPickerButton!=null)
            mPhotoPickerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: Fire an intent to show an image picker
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/jpeg");
                    // intent.setType("image/png");
                    intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                    startActivityForResult(intent.createChooser(intent,"Complete action using"),RC_PHOTO_PICKER);

                }
            });
        date1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    count1 = 2;
                } else {
                    count1 = 0;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }

        });

        send1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count1==2){
                Poll2 poll2 = new Poll2(null,date1.getText().toString(),username1);
                mMessageDatabaseReference.push().setValue(poll2);
                System.out.println("hamza here"+date1.getText());
                    Toast.makeText(getContext(),"Thanks for your vote!"+mUsername, Toast.LENGTH_SHORT).show();
                date1.setText("");}
                else {
                    Toast.makeText(getContext(),"Kindly Give Your Answer!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});



        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    //user is signed in
                    onSignedInInitialize(user.getDisplayName());
                    System.out.println("hamza ahmed123 "+user.getDisplayName());
                    username1 = user.getDisplayName();
                    System.out.println(username1+": username ");
                    if(!user.getDisplayName().equals("K142805 Hamza Ahmed")){
                      //  imageView.setVisibility(View.GONE);
                       // date1.setVisibility(View.GONE);
                        mPhotoPickerButton.setVisibility(View.GONE);
                    }


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

        return view;

    }


    public void onclick1(String username11){

    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == RC_PHOTO_PICKER && resultCode==RESULT_OK){
            Uri selectedImageUri= data.getData();
            StorageReference photoRef =
                    PointsTableStorageReference.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener
                    (getActivity(), new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadURL =taskSnapshot.getDownloadUrl();
                            Poll2 Poll2 = new Poll2(downloadURL.toString(),null,username1);
                            //Log.d("Musername","here-> "+Poll2.getName().substring(7));

                            boolean isPhoto = downloadURL.toString() != null;
                            if (isPhoto) {
                                Toast.makeText(getActivity(), "Loading your picture!!", Toast.LENGTH_SHORT).show();
                                mprogressBar.setVisibility(View.GONE);
                                imageView.setVisibility(View.VISIBLE);
                                Glide.with(imageView.getContext())
                                        .load(Poll2.getPhotoUrl())
                                        .into(imageView);
                                mMessageDatabaseReference2.push().setValue(Poll2);
                            }
                            checking=Poll2.getPhotoUrl();

                            Log.d("Poll21",""+checking);
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
                    Poll2 Poll21 = dataSnapshot.getValue(Poll2.class);

//                    date2.setText(Poll21.getUpdatedDate());
                    imageView.setVisibility(View.VISIBLE);
                    Glide.with(imageView.getContext())
                            .load(Poll21.getPhotoUrl())
                            .into(imageView);

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Poll2 f =dataSnapshot.getValue(Poll2.class);
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
            mMessageDatabaseReference2.addChildEventListener(mChildEventListener);
            mMessageDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                        Poll2 note = noteDataSnapshot.getValue(Poll2.class);


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
        mMessageDatabaseReference2.removeEventListener(mChildEventListener);
        mChildEventListener=null;
    }
    @Override
    public void onPause() {
        super.onPause();
        if (mAuthStateListner != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }
        detachDatabaseReadListener();
    }

    @Override
    public void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }

}