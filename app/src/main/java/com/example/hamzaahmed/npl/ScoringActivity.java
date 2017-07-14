package com.example.hamzaahmed.npl;

import android.support.v4.app.Fragment;

/**
 * Created by Hamza Ahmed on 13-Jul-17.
 */

public class ScoringActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment(){
        return new ScoringFragment();
    }
}
