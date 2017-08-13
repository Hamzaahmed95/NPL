package myApp.hamza.npl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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

import static myApp.hamza.npl.PointsTableFragment.RC_SIGN_IN;

/**
 * Created by Hamza Ahmed on 13-Aug-17.
 */

public class test1  extends AppCompatActivity {
    private FirebaseStorage firebaseStorage;
    private ImageButton p1;
    private ImageButton p2;
    private ImageButton p3;
    private ImageButton p4;
    private ImageButton p5;
    private ImageButton p6;
    private ImageView i1;
    private ImageView i2;
    private ImageView i3;
    private ImageView i4;
    private ImageView i5;
    private ImageView i6;
    private static final int RC_PHOTO_PICKER1 =  1;

    private static final int RC_PHOTO_PICKER2 =  2;

    private static final int RC_PHOTO_PICKER3 =  3;

    private static final int RC_PHOTO_PICKER4 =  4;

    private static final int RC_PHOTO_PICKER5 =  5;

    private static final int RC_PHOTO_PICKER6 =  6;
    private StorageReference PointsTableStorageReference;
    private DatabaseReference mMessageDatabaseReference;
    private StorageReference PointsTableStorageReference2;
    private DatabaseReference mMessageDatabaseReference2;
    private StorageReference PointsTableStorageReference3;
    private DatabaseReference mMessageDatabaseReference3;
    private StorageReference PointsTableStorageReference4;
    private DatabaseReference mMessageDatabaseReference4;
    private StorageReference PointsTableStorageReference5;
    private DatabaseReference mMessageDatabaseReference5;
    private StorageReference PointsTableStorageReference6;
    private DatabaseReference mMessageDatabaseReference6;
    private FirebaseDatabase mFirebaseDatabase;
    private ChildEventListener mChildEventListener;
    private ChildEventListener mChildEventListener2;
    private ChildEventListener mChildEventListener3;
    private ChildEventListener mChildEventListener4;
    private ChildEventListener mChildEventListener5;
    private ChildEventListener mChildEventListener6;
    String url1;
    String url2;
    String url3;
    String url4;
    String url5;
    String url6;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.test1);

        firebaseStorage = FirebaseStorage.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        PointsTableStorageReference =firebaseStorage.getReference().child("top3_Pcaps1");
        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("top3Pcaps1");
        PointsTableStorageReference2 =firebaseStorage.getReference().child("top3_Pcaps2");
        mMessageDatabaseReference2 =mFirebaseDatabase.getReference().child("top3Pcaps2");
        PointsTableStorageReference3 =firebaseStorage.getReference().child("top3_Pcaps3");
        mMessageDatabaseReference3 =mFirebaseDatabase.getReference().child("top3Pcaps3");
        PointsTableStorageReference4 =firebaseStorage.getReference().child("top3_Pcaps4");
        mMessageDatabaseReference4 =mFirebaseDatabase.getReference().child("top3Pcaps4");
        PointsTableStorageReference5 =firebaseStorage.getReference().child("top3_Pcaps5");
        mMessageDatabaseReference5 =mFirebaseDatabase.getReference().child("top3Pcaps5");
        PointsTableStorageReference6 =firebaseStorage.getReference().child("top3_Pcaps6");
        mMessageDatabaseReference6 =mFirebaseDatabase.getReference().child("top3Pcaps6");

        p1 = (ImageButton)findViewById(R.id.photoPickerButton1);
        p2 = (ImageButton)findViewById(R.id.photoPickerButton2);
        p3 = (ImageButton)findViewById(R.id.photoPickerButton3);
        p4 = (ImageButton)findViewById(R.id.photoPickerButton4);
        p5 = (ImageButton)findViewById(R.id.photoPickerButton5);
        p6 = (ImageButton)findViewById(R.id.photoPickerButton6);
    
        i1=(ImageView)findViewById(R.id.image1);
        i2=(ImageView)findViewById(R.id.image2);
        i3=(ImageView)findViewById(R.id.image3);
        i4=(ImageView)findViewById(R.id.image4);
        i5=(ImageView)findViewById(R.id.image5);
        i6=(ImageView)findViewById(R.id.image6);


        Query mHouseDatabaseReference1 =mFirebaseDatabase.getReference().child("top3Pcaps1").limitToLast(1);;

        mHouseDatabaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println(issue.child("photoUrl").getValue());
                        // if(issue.child("photoUrl").getValue().toString()!=null)
                        url1=issue.child("photoUrl").getValue().toString();
                        Glide.with(i1.getContext())
                                .load(url1)
                                .into(i1);
                     }
}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query mHouseDatabaseReference2 =mFirebaseDatabase.getReference().child("top3Pcaps2").limitToLast(1);;

        mHouseDatabaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println(issue.child("photoUrl").getValue());
                        // if(issue.child("photoUrl").getValue().toString()!=null)
                        url2=issue.child("photoUrl").getValue().toString();
                        Glide.with(i2.getContext())
                                .load(url2)
                                .into(i2);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Query mHouseDatabaseReference3 =mFirebaseDatabase.getReference().child("top3Pcaps3").limitToLast(1);;

        mHouseDatabaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println(issue.child("photoUrl").getValue());
                        // if(issue.child("photoUrl").getValue().toString()!=null)
                        url3=issue.child("photoUrl").getValue().toString();
                        Glide.with(i3.getContext())
                                .load(url3)
                                .into(i3);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Query mHouseDatabaseReference4 =mFirebaseDatabase.getReference().child("top3Pcaps4").limitToLast(1);;

        mHouseDatabaseReference4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println(issue.child("photoUrl").getValue());
                        // if(issue.child("photoUrl").getValue().toString()!=null)
                        url4=issue.child("photoUrl").getValue().toString();
                        Glide.with(i4.getContext())
                                .load(url4)
                                .into(i4);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Query mHouseDatabaseReference5 =mFirebaseDatabase.getReference().child("top3Pcaps5").limitToLast(1);;

        mHouseDatabaseReference5.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println(issue.child("photoUrl").getValue());
                        // if(issue.child("photoUrl").getValue().toString()!=null)
                        url5=issue.child("photoUrl").getValue().toString();
                        Glide.with(i5.getContext())
                                .load(url5)
                                .into(i5);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Query mHouseDatabaseReference6 =mFirebaseDatabase.getReference().child("top3Pcaps6").limitToLast(1);;

        mHouseDatabaseReference6.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println(issue.child("photoUrl").getValue());
                        // if(issue.child("photoUrl").getValue().toString()!=null)
                        url6=issue.child("photoUrl").getValue().toString();
                        Glide.with(i6.getContext())
                                .load(url6)
                                .into(i6);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if(p1!=null)
            p1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: Fire an intent to show an image picker
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/jpeg");
                    // intent.setType("image/png");
                    intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                    startActivityForResult(intent.createChooser(intent,"Complete action using"),RC_PHOTO_PICKER1);

                }
            });
        if(p2!=null)
            p2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: Fire an intent to show an image picker
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/jpeg");
                    // intent.setType("image/png");
                    intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                    startActivityForResult(intent.createChooser(intent,"Complete action using"),RC_PHOTO_PICKER2);

                }
            });
        if(p3!=null)
            p3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: Fire an intent to show an image picker
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/jpeg");
                    // intent.setType("image/png");
                    intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                    startActivityForResult(intent.createChooser(intent,"Complete action using"),RC_PHOTO_PICKER3);

                }
            });
        if(p4!=null)
            p4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: Fire an intent to show an image picker
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/jpeg");
                    // intent.setType("image/png");
                    intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                    startActivityForResult(intent.createChooser(intent,"Complete action using"),RC_PHOTO_PICKER4);

                }
            });
        if(p5!=null)
            p5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: Fire an intent to show an image picker
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/jpeg");
                    // intent.setType("image/png");
                    intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                    startActivityForResult(intent.createChooser(intent,"Complete action using"),RC_PHOTO_PICKER5);

                }
            });
        if(p6!=null)
            p6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: Fire an intent to show an image picker
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/jpeg");
                    // intent.setType("image/png");
                    intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                    startActivityForResult(intent.createChooser(intent,"Complete action using"),RC_PHOTO_PICKER6);

                }
            });
        Bundle extra =this.getIntent().getExtras();

        if(extra!=null) {
            String url2 = extra.getString("username");
            Log.d("hamza: ",url2);
            if(!url2.equals("K142805 Hamza Ahmed")){
                p1.setVisibility(View.GONE);
                p2.setVisibility(View.GONE);
                p3.setVisibility(View.GONE);
                p4.setVisibility(View.GONE);
                p5.setVisibility(View.GONE);
                p6.setVisibility(View.GONE);            }
        }
        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    //user is signed in
                    onSignedInInitialize(user.getDisplayName());
                    System.out.println("bhensssss");
                    if(!user.getDisplayName().equals("K142805 Hamza Ahmed")){
                        p1.setVisibility(View.GONE);
                        p2.setVisibility(View.GONE);
                        p3.setVisibility(View.GONE);
                        p4.setVisibility(View.GONE);
                        p5.setVisibility(View.GONE);
                        p6.setVisibility(View.GONE);
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
            }
        };

    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == RC_PHOTO_PICKER1 && resultCode==RESULT_OK){
            Uri selectedImageUri= data.getData();
            StorageReference photoRef =
                    PointsTableStorageReference.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener
                    (this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadURL =taskSnapshot.getDownloadUrl();
                            pointTablePicture pointTablePicture = new pointTablePicture(downloadURL.toString(),null);
                            //Log.d("Musername","here-> "+pointTablePicture.getName().substring(7));

                            boolean isPhoto = downloadURL.toString() != null;
                            if (isPhoto) {
                                Toast.makeText(getApplicationContext(), "Loading your picture!!", Toast.LENGTH_SHORT).show();
                                i1.setVisibility(View.VISIBLE);
                                Glide.with(i1.getContext())
                                        .load(pointTablePicture.getPhotoUrl())
                                        .into(i1);
                                mMessageDatabaseReference.push().setValue(pointTablePicture);
                            }

                          }
                    });
        }
        else if(requestCode == RC_PHOTO_PICKER2 && resultCode==RESULT_OK){
            Uri selectedImageUri= data.getData();
            StorageReference photoRef =
                    PointsTableStorageReference2.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener
                    (this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadURL =taskSnapshot.getDownloadUrl();
                            pointTablePicture pointTablePicture = new pointTablePicture(downloadURL.toString(),null);
                            //Log.d("Musername","here-> "+pointTablePicture.getName().substring(7));

                            boolean isPhoto = downloadURL.toString() != null;
                            if (isPhoto) {
                                Toast.makeText(getApplicationContext(), "Loading your picture!!", Toast.LENGTH_SHORT).show();
                                i2.setVisibility(View.VISIBLE);
                                Glide.with(i2.getContext())
                                        .load(pointTablePicture.getPhotoUrl())
                                        .into(i2);
                                mMessageDatabaseReference2.push().setValue(pointTablePicture);
                            }

                        }
                    });
        }
        else if(requestCode == RC_PHOTO_PICKER3 && resultCode==RESULT_OK){
            Uri selectedImageUri= data.getData();
            StorageReference photoRef =
                    PointsTableStorageReference3.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener
                    (this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadURL =taskSnapshot.getDownloadUrl();
                            pointTablePicture pointTablePicture = new pointTablePicture(downloadURL.toString(),null);
                            //Log.d("Musername","here-> "+pointTablePicture.getName().substring(7));

                            boolean isPhoto = downloadURL.toString() != null;
                            if (isPhoto) {
                                Toast.makeText(getApplicationContext(), "Loading your picture!!", Toast.LENGTH_SHORT).show();
                                i3.setVisibility(View.VISIBLE);
                                Glide.with(i3.getContext())
                                        .load(pointTablePicture.getPhotoUrl())
                                        .into(i3);
                                mMessageDatabaseReference3.push().setValue(pointTablePicture);
                            }

                        }
                    });
        }
        else if(requestCode == RC_PHOTO_PICKER4 && resultCode==RESULT_OK){
            Uri selectedImageUri= data.getData();
            StorageReference photoRef =
                    PointsTableStorageReference4.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener
                    (this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadURL =taskSnapshot.getDownloadUrl();
                            pointTablePicture pointTablePicture = new pointTablePicture(downloadURL.toString(),null);
                            //Log.d("Musername","here-> "+pointTablePicture.getName().substring(7));

                            boolean isPhoto = downloadURL.toString() != null;
                            if (isPhoto) {
                                Toast.makeText(getApplicationContext(), "Loading your picture!!", Toast.LENGTH_SHORT).show();
                                i4.setVisibility(View.VISIBLE);
                                Glide.with(i4.getContext())
                                        .load(pointTablePicture.getPhotoUrl())
                                        .into(i4);
                                mMessageDatabaseReference4.push().setValue(pointTablePicture);
                            }

                        }
                    });
        }
        else if(requestCode == RC_PHOTO_PICKER5 && resultCode==RESULT_OK){
            Uri selectedImageUri= data.getData();
            StorageReference photoRef =
                    PointsTableStorageReference5.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener
                    (this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadURL =taskSnapshot.getDownloadUrl();
                            pointTablePicture pointTablePicture = new pointTablePicture(downloadURL.toString(),null);
                            //Log.d("Musername","here-> "+pointTablePicture.getName().substring(7));

                            boolean isPhoto = downloadURL.toString() != null;
                            if (isPhoto) {
                                Toast.makeText(getApplicationContext(), "Loading your picture!!", Toast.LENGTH_SHORT).show();
                                i5.setVisibility(View.VISIBLE);
                                Glide.with(i5.getContext())
                                        .load(pointTablePicture.getPhotoUrl())
                                        .into(i5);
                                mMessageDatabaseReference5.push().setValue(pointTablePicture);
                            }

                        }
                    });
        }
        else if(requestCode == RC_PHOTO_PICKER6 && resultCode==RESULT_OK){
            Uri selectedImageUri= data.getData();
            StorageReference photoRef =
                    PointsTableStorageReference6.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener
                    (this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadURL =taskSnapshot.getDownloadUrl();
                            pointTablePicture pointTablePicture = new pointTablePicture(downloadURL.toString(),null);
                            //Log.d("Musername","here-> "+pointTablePicture.getName().substring(7));

                            boolean isPhoto = downloadURL.toString() != null;
                            if (isPhoto) {
                                Toast.makeText(getApplicationContext(), "Loading your picture!!", Toast.LENGTH_SHORT).show();
                                i6.setVisibility(View.VISIBLE);
                                Glide.with(i6.getContext())
                                        .load(pointTablePicture.getPhotoUrl())
                                        .into(i6);
                                mMessageDatabaseReference6.push().setValue(pointTablePicture);
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

                    i1.setVisibility(View.VISIBLE);
                    Glide.with(i1.getContext())
                            .load(pointTablePicture1.getPhotoUrl())
                            .into(i1);

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
        if(mChildEventListener2==null) {
            mChildEventListener2 = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    pointTablePicture pointTablePicture1 = dataSnapshot.getValue(pointTablePicture.class);

                    i2.setVisibility(View.VISIBLE);
                    Glide.with(i2.getContext())
                            .load(pointTablePicture1.getPhotoUrl())
                            .into(i2);

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
            mMessageDatabaseReference2.addChildEventListener(mChildEventListener2);
            mMessageDatabaseReference2.addValueEventListener(new ValueEventListener() {
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
        } if(mChildEventListener3==null) {
            mChildEventListener3 = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    pointTablePicture pointTablePicture1 = dataSnapshot.getValue(pointTablePicture.class);

                    i3.setVisibility(View.VISIBLE);
                    Glide.with(i3.getContext())
                            .load(pointTablePicture1.getPhotoUrl())
                            .into(i3);

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
            mMessageDatabaseReference3.addChildEventListener(mChildEventListener3);
            mMessageDatabaseReference3.addValueEventListener(new ValueEventListener() {
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
        } if(mChildEventListener4==null) {
            mChildEventListener4 = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    pointTablePicture pointTablePicture1 = dataSnapshot.getValue(pointTablePicture.class);

                    i4.setVisibility(View.VISIBLE);
                    Glide.with(i4.getContext())
                            .load(pointTablePicture1.getPhotoUrl())
                            .into(i4);

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
            mMessageDatabaseReference4.addChildEventListener(mChildEventListener4);
            mMessageDatabaseReference4.addValueEventListener(new ValueEventListener() {
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
        } if(mChildEventListener5==null) {
            mChildEventListener5 = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    pointTablePicture pointTablePicture1 = dataSnapshot.getValue(pointTablePicture.class);

                    i5.setVisibility(View.VISIBLE);
                    Glide.with(i5.getContext())
                            .load(pointTablePicture1.getPhotoUrl())
                            .into(i5);

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
            mMessageDatabaseReference5.addChildEventListener(mChildEventListener5);
            mMessageDatabaseReference5.addValueEventListener(new ValueEventListener() {
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
        } if(mChildEventListener6==null) {
            mChildEventListener6 = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    pointTablePicture pointTablePicture1 = dataSnapshot.getValue(pointTablePicture.class);

                    i6.setVisibility(View.VISIBLE);
                    Glide.with(i6.getContext())
                            .load(pointTablePicture1.getPhotoUrl())
                            .into(i6);

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
            mMessageDatabaseReference6.addChildEventListener(mChildEventListener6);
            mMessageDatabaseReference6.addValueEventListener(new ValueEventListener() {
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
        mMessageDatabaseReference.removeEventListener(mChildEventListener2);
        mMessageDatabaseReference.removeEventListener(mChildEventListener3);
        mMessageDatabaseReference.removeEventListener(mChildEventListener4);
        mMessageDatabaseReference.removeEventListener(mChildEventListener5);
        mMessageDatabaseReference.removeEventListener(mChildEventListener6);
        mChildEventListener=null;
    }


}