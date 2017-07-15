package com.example.hamzaahmed.npl;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Created by Hamza Ahmed on 15-Jul-17.
 */

public class RecentMatches extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new MatchesFragment();
    }

}
