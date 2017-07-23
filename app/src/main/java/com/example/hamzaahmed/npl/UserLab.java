package com.example.hamzaahmed.npl;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Hamza Ahmed on 23-Jul-17.
 */

public class UserLab {
    private static UserLab UserLab;
    private ArrayList mUser;
    private String [] array1;
    Set<String> hs;
    public UserLab() {

    }


    public UserLab(Context context,String [] array,int count){
        array1=array;
        mUser =new ArrayList<>();

        for(int i = 0; i<count ; i++){
            User User = new User();
            User.setName(array[i]);
            mUser.add(User);
        }
        hs= new HashSet<>();
        hs.addAll(mUser);
        mUser.clear();
        mUser.addAll(hs);
    }

    public List<User> getmUser(){
        return mUser;
    }


}

