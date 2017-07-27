package com.example.hamzaahmed.npl;

import android.support.v4.app.Fragment;

/**
 * Created by Hamza Ahmed on 27-Jul-17.
 */

public class JanbazStatsActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new JanbazStats() ;
    }
}
