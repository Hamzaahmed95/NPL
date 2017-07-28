package com.example.hamzaahmed.npl;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hamza Ahmed on 14-Jul-17.
 */

public class InterviewAdapter extends ArrayAdapter<InterviewClass> {

    ArrayList al = new ArrayList();
    ProgressBar mprogressBar;
    Uri uri;
    private MediaController mediaController;

    public InterviewAdapter(Context context, int resource, List<InterviewClass> objects) {
        super(context, resource, objects);


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_interview, parent, false);
        }
        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageViewMOM);
        VideoView v= (VideoView)convertView.findViewById(R.id.simpleVideoView);
        TextView name = (TextView) convertView.findViewById(R.id.messageTextViewMOM);
        TextView runsAndwicket = (TextView) convertView.findViewById(R.id.nameTextViewMOM);
        mprogressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);
        Log.d("position",""+getItem(position));
        InterviewClass message = getItem(position);

        ObjectAnimator anim = ObjectAnimator.ofInt(mprogressBar, "progress", 0, 100);
        anim.setDuration(4000);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();
        mprogressBar.setVisibility(View.GONE);
        boolean isVideo = message.getPICTURE() != null;
        if(isVideo) {
            mediaController = new MediaController(getContext());
            // Set the videoView that acts as the anchor for the MediaController.
            mediaController.setAnchorView(v);
            v.setMediaController(mediaController);
            v.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                @Override
                public void onPrepared(MediaPlayer mp) {



                    mp.start();

                    mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {

                        @Override
                        public void onVideoSizeChanged(MediaPlayer mp, int arg1, int arg2) {
                            // TODO Auto-generated method stub
                            Log.e("hM", "Changed");

                            mp.start();
                        }
                    });


                }
            });
            v.setVideoPath(message.getPICTURE());
            System.out.println("here"+message.getPICTURE());
            v.requestFocus();
        }
        return convertView;
    }
}
