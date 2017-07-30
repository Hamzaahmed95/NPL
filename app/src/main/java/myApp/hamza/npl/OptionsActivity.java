package myApp.hamza.npl;

import android.support.v4.app.Fragment;

/**
 * Created by Hamza Ahmed on 16-Jul-17.
 */

public class OptionsActivity extends SingleFragmentActivity {



    @Override
    protected Fragment createFragment(){
        return new OptionsFragment();
    }
}
