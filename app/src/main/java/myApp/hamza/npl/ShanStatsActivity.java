package myApp.hamza.npl;

import android.support.v4.app.Fragment;

/**
 * Created by Hamza Ahmed on 27-Jul-17.
 */

public class ShanStatsActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new ShanStats() ;
    }}
