package com.example.hamzaahmed.npl;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private Button getStarted;

    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      getStarted = (Button)findViewById(R.id.getStarted);

        if (isFirstTime()) {

            getStarted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(MainActivity.this, UsernameAndPassword.class);
                    startActivity(i);
                    finish();

                }
            });
            if (!isTaskRoot()
                    && getIntent().hasCategory(Intent.CATEGORY_LAUNCHER)
                    && getIntent().getAction() != null
                    && getIntent().getAction().equals(Intent.ACTION_MAIN)) {

                finish();
                return;
            }

            // show dialog
        }else {
            getStarted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(MainActivity.this, OptionsActivity.class);
                    startActivity(i);
                    finish();
                }
            });
            if (!isTaskRoot()
                    && getIntent().hasCategory(Intent.CATEGORY_LAUNCHER)
                    && getIntent().getAction() != null
                    && getIntent().getAction().equals(Intent.ACTION_MAIN)) {

                finish();
                return;
            }
        }


    }
    private boolean isFirstTime()
    {
        SharedPreferences preferences = this.getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }
}
