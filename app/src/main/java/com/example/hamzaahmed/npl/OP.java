package com.example.hamzaahmed.npl;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

/**
 * Created by Hamza Ahmed on 25-Jul-17.
 */

public class OP extends AppCompatActivity {

    ImageView back;
    LinearLayout orangeCap;
    Dialog dialog;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.op);
        back=(ImageView)findViewById(R.id.backButton15);
        orangeCap = (LinearLayout) findViewById(R.id.orangeCap);

        orangeCap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            showDialog();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OP.this,OptionsActivity.class);
                startActivity(i);
            }
        });
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

