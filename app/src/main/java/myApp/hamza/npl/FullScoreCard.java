package myApp.hamza.npl;

import android.support.v4.app.Fragment;

/**
 * Created by Hamza Ahmed on 22-Jul-17.
 */

public class FullScoreCard extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new FullScoreCardFragment();
    }
}
