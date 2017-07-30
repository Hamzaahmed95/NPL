package myApp.hamza.npl;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Hamza Ahmed on 25-Jul-17.
 */

public class OP extends AppCompatActivity {

    ImageView back;
    LinearLayout orangeCap;
    LinearLayout purpleCap;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    Dialog dialog;
    String name1;
    String url2;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.op);
        back=(ImageView)findViewById(R.id.backButton15);
        orangeCap = (LinearLayout) findViewById(R.id.orangeCap);
        purpleCap = (LinearLayout) findViewById(R.id.purpleCap);
        mFirebaseAuth = FirebaseAuth.getInstance();

        System.out.println("naam hai : "+name1);
        Bundle extra =this.getIntent().getExtras();
        if(extra!=null) {
            url2 = extra.getString("username");
            System.out.println("where ? ? "+url2);
        }

            orangeCap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Intent i = new Intent(OP.this,OrangeCapActivity.class);
                i.putExtra("username",url2);
                startActivity(i);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OP.this,OptionsActivity.class);
                startActivity(i);
            }
        });


        Bundle extra2 =this.getIntent().getExtras();
        if(extra2!=null) {
            String url2 = extra.getString("username");
            Log.d("hamza: ",url2);
        }
        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user!=null){
                    //user is signed in
                    name1 =user.getDisplayName();
                    orangeCap.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent i = new Intent(OP.this,OrangeCapActivity.class);
                            i.putExtra("username",name1);
                            startActivity(i);
                        }
                    });
                    purpleCap.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent i = new Intent(OP.this,PurpleCapActivity.class);
                            i.putExtra("username",name1);
                            startActivity(i);
                        }
                    });

                }
                else{
                    //user is signed out



                }
            };
        };
    }
    @Override
    public void onPause(){
        super.onPause();
        if(mAuthStateListner!=null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }
    }
    public void onResume(){
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }

    private void showDialog() {
        // custom dialog
        dialog = new Dialog( this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.op_caps);

        // set the custom dialog components - text, image and button

        // Close Button

        // Buy Button

        ImageView v1 = (ImageView) dialog.findViewById(R.id.image15);



        ImageButton Close = (ImageButton) dialog.findViewById(R.id.close15);
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.show();
    }
}

