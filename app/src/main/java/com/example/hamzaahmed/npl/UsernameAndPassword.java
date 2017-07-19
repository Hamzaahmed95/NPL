package com.example.hamzaahmed.npl;

import android.support.v4.app.Fragment;

/**
 * Created by Hamza Ahmed on 19-Jul-17.
 */

public class UsernameAndPassword extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment(){
        return new UsernameAndPasswordFragment();
    }
}
