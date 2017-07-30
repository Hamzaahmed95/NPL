package myApp.hamza.npl;

import android.support.v4.app.Fragment;

/**
 * Created by Hamza Ahmed on 29-Jul-17.
 */

public class PollingActivity extends SingleFragmentActivity {



    @Override
    protected Fragment createFragment(){
        return new PollingFragment();
    }
}
