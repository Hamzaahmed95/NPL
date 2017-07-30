package myApp.hamza.npl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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

import static android.app.Activity.RESULT_OK;
import static myApp.hamza.npl.ProfileActivity.ANONYMOUS;
import static myApp.hamza.npl.ProfileActivity.DEFAULT_MSG_LENGTH_LIMIT;

/**
 * Created by Hamza Ahmed on 13-Jul-17.
 */

public class ScoringFragment extends Fragment {

    private TextView Runs1;
    private TextView Overs1;
    private TextView Wicket1;
    private TextView Runs2;
    private TextView Overs2;
    private TextView ball1;
    private TextView ball2;
    private TextView ball3;
    private TextView ball4;
    private TextView ball5;
    private TextView ball6;
    private TextView BatsmanRuns1;
    private TextView BatsmanRuns2;
    private TextView BatsmanRuns3;
    private TextView BatsmanRuns4;
    private TextView BatsmanBalls1;
    private TextView BatsmanBalls2;
    private TextView BatsmanBalls3;
    private TextView BatsmanBalls4;
    private TextView BatsmanOnCrease1;
    private TextView BatsmanOnCrease2;
    private TextView BowlerOnCrease1;
    private TextView BowlerOnCrease2;
    private Button getScoreCard;
    private ImageView imageView;
    private ImageView imageView2;
    private FirebaseStorage firebaseStorage;

    private static final int RC_PHOTO_PICKER = 2;
    private static final int RC_PHOTO_PICKER2 = 3;
    private ImageButton mPhotoPickerButton1;
    private ImageButton mPhotoPickerButton2;
    private StorageReference TeamPlayingStorageReference;
    private TextView Wicket2;
    private EditText scoreInput;
    private EditText scoreInput2;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mScoreDatabaseReference;
    private DatabaseReference mScoreDatabaseReference2;
    private DatabaseReference mScoreDatabaseReference3;
    private DatabaseReference mScoreDatabaseReference4;
    public static final int RC_SIGN_IN =1;
    private ChildEventListener mChildEventListener;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private String mUsername;
    private Button SendButton;
    private Button SendButton2;
    private ImageView backButton4;
    String name;
    String url1;
    String url2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.scorecard, container, false);
        Runs1 = (TextView) view.findViewById(R.id.livsScoreRuns1);
        Runs2 = (TextView) view.findViewById(R.id.livsScoreRuns2);
        Overs1 = (TextView) view.findViewById(R.id.livsScoreOvers1);
        Overs2 = (TextView) view.findViewById(R.id.livsScoreOvers2);
        Wicket1 = (TextView) view.findViewById(R.id.livsScoreWicket1);
        Wicket2 = (TextView) view.findViewById(R.id.livsScoreWicket2);
        firebaseStorage = FirebaseStorage.getInstance();
        TeamPlayingStorageReference =firebaseStorage.getReference().child("teamPlaying");
        Runs1 = (TextView) view.findViewById(R.id.livsScoreRuns1);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mScoreDatabaseReference = mFirebaseDatabase.getReference().child("score");
        mScoreDatabaseReference2 = mFirebaseDatabase.getReference().child("score2");
        mScoreDatabaseReference3 = mFirebaseDatabase.getReference().child("score3");
        mScoreDatabaseReference4 = mFirebaseDatabase.getReference().child("score4");
        mPhotoPickerButton1 = (ImageButton) view.findViewById(R.id.photoPickerTeam1);
        mPhotoPickerButton2 = (ImageButton) view.findViewById(R.id.photoPickerTeam2);
        Runs1 = (TextView) view.findViewById(R.id.livsScoreRuns1);
        scoreInput = (EditText) view.findViewById(R.id.scoreUpdate);
        scoreInput2 = (EditText) view.findViewById(R.id.batsmanUpdate);
        SendButton = (Button) view.findViewById(R.id.scoreSendButton);
        ball1 = (TextView) view.findViewById(R.id.ball1);
        ball2 = (TextView) view.findViewById(R.id.ball2);
        ball3 = (TextView) view.findViewById(R.id.ball3);
        ball4 = (TextView) view.findViewById(R.id.ball4);
        ball5 = (TextView) view.findViewById(R.id.ball5);
        ball6 = (TextView) view.findViewById(R.id.ball6);
        BatsmanOnCrease1 = (TextView)view.findViewById(R.id.batsmanOnCrease1);
        BatsmanOnCrease2 = (TextView)view.findViewById(R.id.batsmanOnCrease2);
        BowlerOnCrease1 = (TextView)view.findViewById(R.id.bowlerOnCreas1);
        BowlerOnCrease2 = (TextView)view.findViewById(R.id.bowlerOnCreas2);
        BatsmanRuns1 = (TextView)view.findViewById(R.id.runs1);
        BatsmanRuns2 = (TextView)view.findViewById(R.id.runs2);
        BatsmanRuns3 = (TextView)view.findViewById(R.id.runs3);
        BatsmanRuns4 = (TextView)view.findViewById(R.id.runs4);
        BatsmanBalls1=(TextView)view.findViewById(R.id.balls1);
        BatsmanBalls2=(TextView)view.findViewById(R.id.balls2);
        BatsmanBalls3=(TextView)view.findViewById(R.id.balls3);
        BatsmanBalls4=(TextView)view.findViewById(R.id.balls4);
        imageView=(ImageView)view.findViewById(R.id.teamplaying1);
        imageView2=(ImageView)view.findViewById(R.id.teamplaying2);
        SendButton2 = (Button)view.findViewById(R.id.batsmanSendButton);
        Query mHouseDatabaseReference2 =mFirebaseDatabase.getReference().child("score3").limitToLast(1);;

        mHouseDatabaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hamza here"+issue.getValue());
                        if(issue.child("url1").getValue().toString()!=null)
                            url1=issue.child("url1").getValue().toString();
                        Glide.with(imageView.getContext())
                                .load(url1)
                                .into(imageView);
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
        Query mHouseDatabaseReference4 =mFirebaseDatabase.getReference().child("score4").limitToLast(1);;

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
                        Glide.with(imageView2.getContext())
                                .load(url2)
                                .into(imageView2);
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
        Query mHouseDatabaseReference =mFirebaseDatabase.getReference().child("score2").limitToLast(1);;

        mHouseDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hamza here2"+issue.getValue());
                        BatsmanOnCrease1.setText(issue.child("batsman1").getValue().toString());
                        BatsmanOnCrease2.setText(issue.child("batsman2").getValue().toString());
                        BowlerOnCrease1.setText(issue.child("bowler1").getValue().toString());
                      //  BowlerOnCrease2.setText(issue.child("bowler2").getValue().toString());


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


        SendButton2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String score = scoreInput2.getText().toString();
                        String[] arr = score.split("-");
                        String Batsman1 = arr[0];
                        String Batsman2 = arr[1];
                        String Bowler1 = arr[2];
                        String Bowler2 = arr[3];

                        BatsmanBowler batsmanBowler = new BatsmanBowler(Batsman1,Batsman2,Bowler1,Bowler2,null,null);
                        mScoreDatabaseReference2.push().setValue(batsmanBowler);
                        scoreInput2.setText("");

                        BatsmanOnCrease1.setText(Batsman1);
                        BatsmanOnCrease2.setText(Batsman2);
                        BowlerOnCrease1.setText(Bowler1);

                    }
                });



        backButton4 =(ImageView) view.findViewById(R.id.backButton4);

        backButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), OptionsActivity.class);
                startActivity(i);
            }
        });

        getScoreCard = (Button) view.findViewById(R.id.getFullScoreCard);



        mUsername = ANONYMOUS;
        SendButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String score = scoreInput.getText().toString();
                        String[] arr = score.split("-");
                        String runs = arr[0];
                        String wicket = arr[1];
                        String overs = arr[2];
                        String team = arr[3];
                        String balls = arr[4];
                        String ballNo = arr[5];
                        String batsmanRuns1 = arr[6];
                        String batsmanRuns2= arr[7];
                        String batsmanBalls1 = arr[8];
                        String batsmanBalls2 = arr[9];
                        LiveScore liveScore = new LiveScore(Integer.parseInt(runs), Integer.parseInt(wicket), Float.parseFloat(overs), 1, Integer.parseInt(team), Integer.parseInt(balls), Integer.parseInt(ballNo),
                                Integer.parseInt(batsmanRuns1), Integer.parseInt(batsmanRuns2), Integer.parseInt(batsmanBalls1), Integer.parseInt(batsmanBalls2));
                        mScoreDatabaseReference.push().setValue(liveScore);
                        scoreInput.setText("");
                        if (Integer.parseInt(team) == 1) {
                            Runs1.setText(runs);
                            Wicket1.setText(wicket);
                            Overs1.setText(overs);
                            BatsmanRuns1.setText(batsmanRuns1);
                            BatsmanRuns2.setText(batsmanRuns2);
                            BatsmanBalls1.setText(batsmanBalls1);
                            BatsmanBalls2.setText(batsmanBalls2);

                        } else {

                            Runs2.setText(runs);
                            Wicket2.setText(wicket);
                            Overs2.setText(overs);
                            BatsmanRuns3.setText(batsmanRuns1);
                         //   BatsmanRuns4.setText(batsmanRuns2);
                            BatsmanBalls3.setText(batsmanBalls1);
                           // BatsmanBalls4.setText(batsmanBalls2);
                        }
                        if (Integer.parseInt(ballNo) == 1) {
                            ball1.setText(balls);
                        } else if (Integer.parseInt(ballNo) == 2) {
                            ball2.setText(balls);
                        } else if (Integer.parseInt(ballNo) == 3) {
                            ball3.setText(balls);
                        } else if (Integer.parseInt(ballNo) == 4) {
                            ball4.setText(balls);
                        } else if (Integer.parseInt(ballNo) == 5) {
                            ball5.setText(balls);
                        } else if (Integer.parseInt(ballNo) == 6) {
                            ball6.setText(balls);
                        }


                    }
                });
        if (scoreInput != null) {
            scoreInput.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.toString().trim().length() > 0) {
                        SendButton.setEnabled(true);
                    } else {
                        SendButton.setEnabled(false);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }

            });
            scoreInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        }

        if (scoreInput2 != null) {
            scoreInput2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.toString().trim().length() > 0) {
                        SendButton2.setEnabled(true);
                    } else {
                        SendButton2.setEnabled(false);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }

            });
            scoreInput2.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        }
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

        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //user is signed in
                    onSignedInInitialize(user.getDisplayName());
                    Log.d("user: ", user.getDisplayName());
                    name=user.getDisplayName();
                    if (!user.getDisplayName().equals("K142805 Hamza Ahmed")) {
                        scoreInput.setVisibility(View.GONE);
                        scoreInput2.setVisibility(View.GONE);
                        mPhotoPickerButton1.setVisibility(View.GONE);
                        mPhotoPickerButton2.setVisibility(View.GONE);
                        SendButton.setVisibility(View.GONE);
                        SendButton2.setVisibility(View.GONE);
                    }
                    getScoreCard.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getActivity(), FullScoreCard.class);
                            i.putExtra("username",name);
                            startActivity(i);
                        }
                    });


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
                            BatsmanBowler batsmanBowler = new BatsmanBowler(null,null,null,null,downloadURL.toString(),null);
                            //Log.d("Musername","here-> "+pointTablePicture.getName().substring(7));

                            boolean isPhoto = downloadURL.toString() != null;
                            if (isPhoto) {
                                Toast.makeText(getActivity(), "Loading your picture!!", Toast.LENGTH_SHORT).show();
                                imageView.setVisibility(View.VISIBLE);
                                Glide.with(imageView.getContext())
                                        .load(batsmanBowler.getUrl1())
                                        .into(imageView);
                                mScoreDatabaseReference3.push().setValue(batsmanBowler);
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
                            BatsmanBowler batsmanBowler = new BatsmanBowler(null,null,null,null,null,downloadURL.toString());
                            //Log.d("Musername","here-> "+pointTablePicture.getName().substring(7));

                            boolean isPhoto = downloadURL.toString() != null;
                            if (isPhoto) {
                                Toast.makeText(getActivity(), "Loading your picture!!", Toast.LENGTH_SHORT).show();
                                imageView2.setVisibility(View.VISIBLE);
                                Glide.with(imageView2.getContext())
                                        .load(batsmanBowler.getUrl2())
                                        .into(imageView2);
                                mScoreDatabaseReference4.push().setValue(batsmanBowler);
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
                    LiveScore liveScore = dataSnapshot.getValue(LiveScore.class);
                    //   BatsmanBowler batsmanBowler = dataSnapshot.getValue(BatsmanBowler.class);
                    if(liveScore.getTeam()!=null) {
                        if (liveScore.getTeam() == 1) {
                            Runs1.setText(String.valueOf(liveScore.getRuns()));
                            Wicket1.setText(String.valueOf(liveScore.getWicket()));
                            Overs1.setText(String.valueOf(liveScore.getOvers()));
                            BatsmanRuns1.setText(String.valueOf(liveScore.getRuns1()));
                            BatsmanRuns2.setText(String.valueOf(liveScore.getRuns2()));
                            BatsmanBalls1.setText(String.valueOf(liveScore.getBall1()));
                            BatsmanBalls2.setText(String.valueOf(liveScore.getBall2()));
                        } else {
                            Runs2.setText(String.valueOf(liveScore.getRuns()));
                            Wicket2.setText(String.valueOf(liveScore.getWicket()));
                            Overs2.setText(String.valueOf(liveScore.getOvers()));
                            BatsmanRuns3.setText(String.valueOf(liveScore.getRuns1()));
                           // BatsmanRuns4.setText(String.valueOf(liveScore.getRuns2()));
                            BatsmanBalls3.setText(String.valueOf(liveScore.getBall1()));
                           //
                            // BatsmanBalls4.setText(String.valueOf(liveScore.getBall2()));
                        }
                    }
                    if(liveScore.getBallNo()!=null) {
                        if (liveScore.getBallNo() == 1) {
                            ball1.setText(String.valueOf(liveScore.getBalls()));
                        } else if (liveScore.getBallNo() == 2) {
                            ball2.setText(String.valueOf(liveScore.getBalls()));
                        } else if (liveScore.getBallNo() == 3) {
                            ball3.setText(String.valueOf(liveScore.getBalls()));
                        } else if (liveScore.getBallNo() == 4) {
                            ball4.setText(String.valueOf(liveScore.getBalls()));
                        } else if (liveScore.getBallNo() == 5) {
                            ball5.setText(String.valueOf(liveScore.getBalls()));
                        } else if (liveScore.getBallNo() == 6) {
                            ball6.setText(String.valueOf(liveScore.getBalls()));
                        }
                    }


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
            mScoreDatabaseReference.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference2.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference3.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference4.addChildEventListener(mChildEventListener);
        }
    }
    private void detachDatabaseReadListener(){
        if(mChildEventListener!=null)
            mScoreDatabaseReference.removeEventListener(mChildEventListener);
        mScoreDatabaseReference2.removeEventListener(mChildEventListener);
        mScoreDatabaseReference3.addChildEventListener(mChildEventListener);
        mScoreDatabaseReference4.addChildEventListener(mChildEventListener);
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
