package com.example.hamzaahmed.npl;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

/**
 * Created by Hamza Ahmed on 16-Jul-17.
 */

public class ImageLayout extends SingleFragmentActivity {



    @Override
    protected Fragment createFragment(){
        return new ImageLayoutFragment();
    }
}
