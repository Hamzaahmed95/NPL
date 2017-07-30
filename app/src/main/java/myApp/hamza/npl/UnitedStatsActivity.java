package myApp.hamza.npl;

import android.support.v4.app.Fragment;

/**
 * Created by Hamza Ahmed on 27-Jul-17.
 */

public class UnitedStatsActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new UnitedStats() ;
    }
}
