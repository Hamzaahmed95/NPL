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

import java.util.ArrayList;
import java.util.List;



public class FullScorecard2 extends AppCompatActivity {

    private static final String TAG = "FullScorecard2";
    private static final String ANONYMOUS ="anonymous" ;
    private ListView mMessageListView;
    private ListView mMessageListView2;
    private ScoringAdapter mScoringAdapter;
    private ScoringAdapter2 mScoringAdapter2;
    private EditText mMessageEditText2;
    private EditText mMessageEditText;
    private Button mSendButton2;
    private Button mSendButton;
    private FirebaseDatabase mFirebaseDatabase;
    public static final int RC_SIGN_IN =1;
    private String mUsername;
    private TextView total;
    private DatabaseReference mMessageDatabaseReference;
    private DatabaseReference mMessageDatabaseReference1;
    private DatabaseReference mMessageDatabaseReference2;
    private ChildEventListener mChildEventListener;
    private ChildEventListener mChildEventListener2;
    private ChildEventListener mChildEventListener1;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private FirebaseStorage firebaseStorage;
    public String NAME;
    private EditText editExtras;
    private TextView extras;
    @Override
    protected void onCreate( final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_scorecard);
        NAME = ANONYMOUS;
        mMessageListView = (ListView) findViewById(R.id.ScoreListView);
        mMessageEditText = (EditText) findViewById(R.id.scoring);
        mSendButton = (Button) findViewById(R.id.sendScore);
        total = (TextView)findViewById(R.id.total);
        mMessageListView2 = (ListView) findViewById(R.id.ScoreListView2);
        mMessageEditText2 = (EditText) findViewById(R.id.scoring2);
        mSendButton2 = (Button) findViewById(R.id.sendScore2);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        extras = (TextView) findViewById(R.id.extras);
        mUsername = ANONYMOUS;
        editExtras = (EditText) findViewById(R.id.editExtras);
        firebaseStorage = FirebaseStorage.getInstance();
        mMessageDatabaseReference = mFirebaseDatabase.getReference().child("liveScoring");
        mMessageDatabaseReference2 = mFirebaseDatabase.getReference().child("liveScoring2");
        mMessageDatabaseReference1 = mFirebaseDatabase.getReference().child("extras");

        Query mHouseDatabaseReference = mFirebaseDatabase.getReference().child("extras").limitToLast(1);
        ;

        mHouseDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        if (!issue.child("extras").getValue().toString().equals(null))
                            extras.setText(issue.child("extras").getValue().toString());

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
        }
        if (mMessageEditText2 != null) {
            mMessageEditText2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.toString().trim().length() > 0) {
                        mSendButton2.setEnabled(true);
                    } else {
                        mSendButton2.setEnabled(false);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }

            });
        }
        // Send button sends a message and clears the EditText
        if (mSendButton != null)
            mSendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String score = mMessageEditText.getText().toString();
                    String[] arr = score.split("-");
                    String Batsman = arr[0];
                    String Type = arr[1];
                    String R2 = arr[2];
                    String B = arr[3];
                    String F4 = arr[4];
                    String S6 = arr[5];
                    String SS = arr[6];


                    FullScoringClass fullScoringClass = new FullScoringClass(Batsman, Type, Integer.parseInt(R2), Integer.parseInt(B), Integer.parseInt(F4), Integer.parseInt(S6), Integer.parseInt(SS));
                    mMessageDatabaseReference.push().setValue(fullScoringClass);
                    mMessageEditText.setText("");
                }
            });

        if (mSendButton2 != null)
            mSendButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String score = mMessageEditText2.getText().toString();
                    String[] arr = score.split("-");
                    String Bowler = arr[0];
                    String overs = arr[1];
                    String maiden = arr[2];
                    String Runs = arr[3];
                    String Wicket = arr[4];
                    String Econ = arr[5];
                    String Zeros = arr[6];
                    String Wide = arr[7];
                    String NoBall = arr[8];


                    FullScoringClass2 fullScoringClass2 = new FullScoringClass2(Bowler, Float.parseFloat(overs), Integer.parseInt(maiden),
                            Integer.parseInt(Runs), Integer.parseInt(Wicket), Float.parseFloat(Econ), Integer.parseInt(Zeros), Integer.parseInt(Wide),
                            Integer.parseInt(NoBall));
                    mMessageDatabaseReference2.push().setValue(fullScoringClass2);
                    mMessageEditText2.setText("");
                    extras extra = new extras(editExtras.getText().toString());
                    mMessageDatabaseReference1.push().setValue(extra);
                    editExtras.setText("");
                }
            });

        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //user is signed in
                    onSignedInInitialize(user.getDisplayName());

                    if (!user.getDisplayName().equals("K142805 Hamza Ahmed")) {
                        mMessageEditText.setVisibility(View.GONE);
                        mSendButton.setVisibility(View.GONE);
                        editExtras.setVisibility(View.GONE);
                        mMessageEditText2.setVisibility(View.GONE);
                        mSendButton2.setVisibility(View.GONE);
                    }

                    final List<FullScoringClass> FullScoreCardClass = new ArrayList<>();
                    mScoringAdapter = new ScoringAdapter(FullScorecard2.this, R.layout.table_layout_recylerview, FullScoreCardClass, NAME);
                    if (mMessageListView != null)
                        mMessageListView.setAdapter(mScoringAdapter);

                    final List<FullScoringClass2> FullScoreCardClass2 = new ArrayList<>();
                    mScoringAdapter2 = new ScoringAdapter2(FullScorecard2.this, R.layout.table_layout_recylerview2, FullScoreCardClass2, NAME);


                    if (mMessageListView2 != null)
                        mMessageListView2.setAdapter(mScoringAdapter2);
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

        Bundle extra = this.getIntent().getExtras();
        if (extra != null) {
            String runs1 = extra.getString("runs1");
            String wicket1 = extra.getString("wickets1");
            String overs1 = extra.getString("overs1");
            String hamza = extra.getString("hamza");
            String score1=""+runs1+"/"+wicket1+"("+overs1+"."+hamza+") Overs";
            System.out.println("score = >"+score1);
            String runs2 = extra.getString("runs2");
            String wicket2 = extra.getString("wickets2");
            String overs22 = extra.getString("overs22");
            String overs2 = extra.getString("overs2");
            String score2=""+runs2+"/"+wicket2+"("+overs2+"."+overs22+") Overs";
            System.out.println("score2 = >"+score2);
            if(extra.getString("id").equals("1")){
                total.setText(score1);
            }
            else{
                total.setText(score2);
            }


        }
    }


    @Override
    protected void onPause(){
        super.onPause();
        if(mAuthStateListner!=null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }
        detachDatabaseReadListener();
        mScoringAdapter.clear();
        mScoringAdapter2.clear();
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
        mScoringAdapter.clear();
        mScoringAdapter2.clear();

        detachDatabaseReadListener();
    }
    private void attachDatabaseReadListener(){
        if(mChildEventListener1==null) {
            mChildEventListener1 = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    extras extra = dataSnapshot.getValue(extras.class);
                    extras.setText(extra.getExtras().toString());
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    // FullScoringClass f =dataSnapshot.getValue(FullScoringClass.class);

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

            mMessageDatabaseReference1.addChildEventListener(mChildEventListener1);
            mMessageDatabaseReference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


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
                    FullScoringClass2 FullScoringClass2 = dataSnapshot.getValue(FullScoringClass2.class);
                    mScoringAdapter2.add(FullScoringClass2);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    // FullScoringClass f =dataSnapshot.getValue(FullScoringClass.class);

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
                    FullScoringClass FullScoringClass = dataSnapshot.getValue(FullScoringClass.class);
                    mScoringAdapter.add(FullScoringClass);
                  }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    // FullScoringClass f =dataSnapshot.getValue(FullScoringClass.class);

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
        mMessageDatabaseReference2.removeEventListener(mChildEventListener2);
        mMessageDatabaseReference1.removeEventListener(mChildEventListener1);
        mChildEventListener=null;
    }


}

