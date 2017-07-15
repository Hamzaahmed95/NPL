package com.example.hamzaahmed.npl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button getStarted;
    private Button getGetStarted;
    private Button getGetStarted2;
    private Button getGetStarted3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      getStarted = (Button)findViewById(R.id.getStarted);
        getGetStarted3 = (Button)findViewById(R.id.LiveScore);

        getGetStarted3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ScoringActivity.class);
                startActivity(i);
            }
        });

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ProfileActivity.class);
                finish();
                startActivity(i);
            }
        });
        getGetStarted = (Button)findViewById(R.id.getStarted2);

        getGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Teams.class);
                startActivity(i);
            }
        });

        getGetStarted2 = (Button)findViewById(R.id.getStarted3);

        getGetStarted2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,RecentMatches.class);
                startActivity(i);
            }
        });

    }
}
