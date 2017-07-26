package com.example.hamzaahmed.npl;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hamza Ahmed on 14-Jul-17.
 */

public class MOMAdapter extends ArrayAdapter<MOMCLASS> {

    ArrayList al = new ArrayList();
    ProgressBar mprogressBar;
    public MOMAdapter(Context context, int resource, List<MOMCLASS> objects) {
        super(context, resource, objects);


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_mom, parent, false);
        }
        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageViewMOM);
        TextView name = (TextView) convertView.findViewById(R.id.messageTextViewMOM);
        TextView runsAndwicket = (TextView) convertView.findViewById(R.id.nameTextViewMOM);
        mprogressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);
        Log.d("position",""+getItem(position));
        MOMCLASS message = getItem(position);

        ObjectAnimator anim = ObjectAnimator.ofInt(mprogressBar, "progress", 0, 100);
        anim.setDuration(4000);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();
        boolean isPhoto = message.getPICTURE() != null;
        if (isPhoto) {
            Glide.with(photoImageView.getContext())
                    .load(message.getPICTURE())
                    .placeholder(R.drawable.circulr_screen)
                    .into(photoImageView);
            runsAndwicket.setText(message.getWicketsAndRuns());
            name.setText(message.getNAME());
            if(mprogressBar!=null)
            mprogressBar.setVisibility(convertView.INVISIBLE);

        }
        else{


        }




        return convertView;
    }
}
