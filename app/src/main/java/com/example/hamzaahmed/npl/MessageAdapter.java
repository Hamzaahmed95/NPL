package com.example.hamzaahmed.npl;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hamza Ahmed on 14-Jul-17.
 */

public class MessageAdapter extends ArrayAdapter<FriendlyMessage> {

    String Name;
    private LinearLayout l1;
    List<FriendlyMessage> f2;
    private TextView date1;

    ArrayList al = new ArrayList();
    public MessageAdapter(Context context, int resource, List<FriendlyMessage> objects,String name,List<FriendlyMessage> f1) {
        super(context, resource, objects);
        Name=name;
        f2=f1;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_message, parent, false);
        }
        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        final ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.progress);
        date1=(TextView)convertView.findViewById(R.id.Date1);
        TextView authorTextView = (TextView) convertView.findViewById(R.id.nameTextView);
      //  l1=(LinearLayout)convertView.findViewById(R.id.rightLayout);
        Log.d("position",""+getItem(position));
        FriendlyMessage message = getItem(position);
        FriendlyMessage m = new FriendlyMessage();
    int count=0;

        for(int i=0;i<f2.size();i++){
            Log.d("adapter",""+f2.get(i).getName());
            if(f2.get(i).getName().equals(Name)){
                count++;
                Log.d("adapt2",""+Name+""+f2.get(i).getName());
               // l1.setGravity(Gravity.RIGHT);
                Log.d("count = ",""+count);
            }
        }


        boolean isPhoto = message.getPhotoUrl() != null;
        if (isPhoto) {
            messageTextView.setVisibility(View.GONE);
            if(message.getDate1()!=null)
            date1.setText(message.getDate1());
            photoImageView.setVisibility(View.VISIBLE);
            Glide.with(photoImageView.getContext())
                    .load(message.getPhotoUrl())
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(photoImageView);
        } else {
            messageTextView.setVisibility(View.VISIBLE);
            photoImageView.setVisibility(View.GONE);
            if(message.getDate1()!=null)
            date1.setText(message.getDate1());
            progressBar.setVisibility(View.GONE);
            messageTextView.setText(message.getText());
        }
        authorTextView.setText(message.getName());




        return convertView;
    }
}
