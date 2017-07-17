package com.example.hamzaahmed.npl;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Hamza Ahmed on 16-Jul-17.
 */

public class ImageLayoutFragment extends Fragment {


    private ImageView image1;
    private ImageView image2;
    AnimatorSet set;
    AnimatorSet set2;
    AnimatorSet set3;

    AnimatorSet set4;

    AnimatorSet set5;

    AnimatorSet set6;
    AnimatorSet set7;
    AnimatorSet set8;
    AnimatorSet set9;
    AnimatorSet set10;
    AnimatorSet set11;
    AnimatorSet set12;
    AnimatorSet set13;
    AnimatorSet set14;
    AnimatorSet set15;
    AnimatorSet set16;
    AnimatorSet set17;
    AnimatorSet set18;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.image_layout, container, false);

        image1 = (ImageView)view.findViewById(R.id.image01);
        image2 = (ImageView)view.findViewById(R.id.image02);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FlipAnimation flipAnimation = new FlipAnimation(image1,image2);
                if (image1.getVisibility() == View.GONE) {
                    flipAnimation.reverse();
                }else{
                    image2.startAnimation(flipAnimation);
                }
            }
        });

        return view;

    }
    public void onStart(){
        super.onStart();
        ImageView imgView=(ImageView)getView().findViewById(R.id.image01);
        ImageView imgView2=(ImageView)getView().findViewById(R.id.image02);
        ImageView imgView3=(ImageView)getView().findViewById(R.id.image03);
        ImageView imgView4=(ImageView)getView().findViewById(R.id.image04);
        ImageView imgView5=(ImageView)getView().findViewById(R.id.image05);
        ImageView imgView6=(ImageView)getView().findViewById(R.id.image06);
        ImageView imgView7=(ImageView)getView().findViewById(R.id.image07);
        ImageView imgView8=(ImageView)getView().findViewById(R.id.image08);
        ImageView imgView9=(ImageView)getView().findViewById(R.id.image09);
        ImageView imgView10=(ImageView)getView().findViewById(R.id.image10);
        ImageView imgView11=(ImageView)getView().findViewById(R.id.image11);
        ImageView imgView12=(ImageView)getView().findViewById(R.id.image12);
        ImageView imgView13=(ImageView)getView().findViewById(R.id.image13);
        ImageView imgView14=(ImageView)getView().findViewById(R.id.image14);
        ImageView imgView15=(ImageView)getView().findViewById(R.id.image15);
        ImageView imgView16=(ImageView)getView().findViewById(R.id.image16);
        ImageView imgView17=(ImageView)getView().findViewById(R.id.image17);
        ImageView imgView18=(ImageView)getView().findViewById(R.id.image18);

        set = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip);
        set2 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip2);
        set.setTarget(imgView);
        set2.setTarget(imgView2);
        set.start();
        set2.start();
        set3 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip1);
        set4 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip4);
        set3.setTarget(imgView3);
        set4.setTarget(imgView4);
        set3.start();
        set4.start();
        set5 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip3);
        set6 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip5);
        set5.setTarget(imgView5);
        set6.setTarget(imgView6);
        set5.start();
        set6.start();
        set7 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip7);
        set8 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip8);
        set7.setTarget(imgView7);
        set8.setTarget(imgView8);
        set7.start();
        set8.start();
        set9 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip9);
        set10 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip10);
        set9.setTarget(imgView9);
        set10.setTarget(imgView10);
        set9.start();
        set10.start();
        set11 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip11);
        set12 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip12);
        set11.setTarget(imgView11);
        set12.setTarget(imgView12);
        set11.start();
        set12.start();
        set13 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip13);
        set14 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip14);
        set13.setTarget(imgView13);
        set14.setTarget(imgView14);
        set13.start();
        set14.start();
        set15 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip15);
        set16 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip16);
        set15.setTarget(imgView15);
        set16.setTarget(imgView16);
        set15.start();
        set16.start();
        set17 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip17);
        set18 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(),R.animator.flip6);
        set17.setTarget(imgView17);
        set18.setTarget(imgView18);
        set17.start();
        set18.start();

    }
}