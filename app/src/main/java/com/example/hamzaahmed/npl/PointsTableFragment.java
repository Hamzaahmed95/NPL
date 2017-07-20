package com.example.hamzaahmed.npl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Hamza Ahmed on 18-Jul-17.
 */

public class PointsTableFragment extends Fragment {



    private FirebaseStorage firebaseStorage;
    private TextView date2;
    private StorageReference PointsTableStorageReference;
    private Button send1;
    private DatabaseReference mMessageDatabaseReference;
    private FirebaseDatabase mFirebaseDatabase;
    private EditText date1;
    private ChildEventListener mChildEventListener;
    private ImageButton mPhotoPickerButton;
    private ImageView imageView;
    public static final int RC_SIGN_IN =1;
    private FirebaseAuth mFirebaseAuth;
    private String url;


    private FirebaseAuth.AuthStateListener mAuthStateListner;

    private static final int RC_PHOTO_PICKER =  2;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.points_table, container, false);
        url="https://firebasestorage.googleapis.com/v0/b/npl2017-2bca3.appspot.com/o/point_table%2Fimage%3A7088?alt=media&token=76b9703d-8214-4d23-a059-c61be20c6b05";
        firebaseStorage = FirebaseStorage.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        PointsTableStorageReference =firebaseStorage.getReference().child("point_table");
        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("pointstable");
        date1 =(EditText)view.findViewById(R.id.date1);
        date2 =(TextView)view.findViewById(R.id.date2);
        imageView =(ImageView)view.findViewById(R.id.photoImageView2);

        mPhotoPickerButton = (ImageButton) view.findViewById(R.id.photoPickerButton2);

        mFirebaseAuth = FirebaseAuth.getInstance();
        Bundle extra =getActivity().getIntent().getExtras();
        if(extra!=null) {
            String url2 = extra.getString("username");
            Log.d("hamza: ",url2);
            send1=(Button)view.findViewById(R.id.send1);
            if(!url2.equals("K142805 Hamza Ahmed")){
                send1.setVisibility(View.GONE);
                date1.setVisibility(View.GONE);
                mPhotoPickerButton.setVisibility(View.GONE);
            }
        }
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);


        send1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pointTablePicture pointTablePicture = new pointTablePicture(null,date1.getText().toString());
                // Clear input box
                mMessageDatabaseReference.push().setValue(pointTablePicture);

                date2.setText(pointTablePicture.getUpdatedDate());
                date1.setText("");

            }
        });


        if(mPhotoPickerButton!=null)
            mPhotoPickerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: Fire an intent to show an image picker
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/jpeg");
                    intent.setType("image/png");
                    intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                    startActivityForResult(intent.createChooser(intent,"Complete action using"),RC_PHOTO_PICKER);

                }
            });




        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    //user is signed in
                    onSignedInInitialize(user.getDisplayName());
                    pointTablePicture p1 = new pointTablePicture();
                    Log.d("hamza ahmed",user.getDisplayName());
                    if(!user.getDisplayName().equals("K142805 Hamza Ahmed")){
                        imageView.setVisibility(View.GONE);
                        date1.setVisibility(View.GONE);
                        mPhotoPickerButton.setVisibility(View.GONE);
                    }

                    Glide.with(imageView.getContext())
                            .load(p1.getPhotoUrl())
                            .into(imageView);


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
                            pointTablePicture pointTablePicture = new pointTablePicture(downloadURL.toString(),null);
                            //Log.d("Musername","here-> "+pointTablePicture.getName().substring(7));

                            boolean isPhoto = downloadURL.toString() != null;
                            if (isPhoto) {
                                Toast.makeText(getActivity(), "Loading your picture!!", Toast.LENGTH_SHORT).show();
                                imageView.setVisibility(View.VISIBLE);
                                Glide.with(imageView.getContext())
                                        .load(pointTablePicture.getPhotoUrl())
                                        .into(imageView);
                                mMessageDatabaseReference.push().setValue(pointTablePicture);
                            }
                        }
                    });
        }
    }
    @Override
    public void onResume(){
        super.onResume();
    }

    private void  onSignedInInitialize(String username){

        attachDatabaseReadListener();

    }
    private void  onSignedOutInitialize(){

        detachDatabaseReadListener();
    }
    private void attachDatabaseReadListener(){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    pointTablePicture pointTablePicture1 = dataSnapshot.getValue(pointTablePicture.class);

                        date2.setText(pointTablePicture1.getUpdatedDate());
                        imageView.setVisibility(View.VISIBLE);
                        Glide.with(imageView.getContext())
                                .load(pointTablePicture1.getPhotoUrl())
                                .into(imageView);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    pointTablePicture f =dataSnapshot.getValue(pointTablePicture.class);
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
                        pointTablePicture note = noteDataSnapshot.getValue(pointTablePicture.class);

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