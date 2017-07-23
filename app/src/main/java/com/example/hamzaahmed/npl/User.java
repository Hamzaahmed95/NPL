package com.example.hamzaahmed.npl;

/**
 * Created by Hamza Ahmed on 23-Jul-17.
 */

public class User {

    private String name1;
    public User(){

    }
    public User(String name){
        name1=name;
    }

    public String getName() {
        return name1;
    }

    public void setName(String name) {
        this.name1 = name;
    }
}
