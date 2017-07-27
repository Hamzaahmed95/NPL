package com.example.hamzaahmed.npl;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Hamza Ahmed on 27-Jul-17.
 */

public class SultanStatsActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new SultanStats() ;
    }
}
