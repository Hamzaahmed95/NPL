package com.example.hamzaahmed.npl;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

/**
 * Created by Hamza Ahmed on 14-Jul-17.
 */

public class HashtagAdapter extends ArrayAdapter<FriendlyHashtag> {

    String Name;
    FirebaseUser user;


    public HashtagAdapter(Context context, int resource, List<FriendlyHashtag> objects, String name) {
        super(context, resource, objects);
        Name = name;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_message2, parent, false);
        }

        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageViewHashtag);
        LinearLayout l1= (LinearLayout)convertView.findViewById(R.id.rightLayoutHashtag);
        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextViewHashtag);
        TextView authorTextView = (TextView) convertView.findViewById(R.id.nameTextViewHashtag);

        FriendlyHashtag message = getItem(position);

        boolean isPhoto = message.getPhotoUrl()!= null;

            if (isPhoto) {
                messageTextView.setVisibility(View.GONE);
                photoImageView.setVisibility(View.VISIBLE);
                Glide.with(photoImageView.getContext())
                        .load(message.getPhotoUrl())
                        .placeholder(R.drawable.loading_spinner)
                        .into(photoImageView);
            } else {
                messageTextView.setVisibility(View.VISIBLE);
                photoImageView.setVisibility(View.GONE);
                messageTextView.setText(message.getText());
            }
            authorTextView.setText(message.getName());


        System.out.println("hello : "+Name);
        if(!Name.equals("K142805 Hamza Ahmed")){
            messageTextView.setVisibility(View.GONE);
            authorTextView.setVisibility(View.GONE);
            photoImageView.setVisibility(View.GONE);
            l1.setVisibility(View.GONE);
        }
        return convertView;

        }

}
