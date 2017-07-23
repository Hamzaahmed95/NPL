package com.example.hamzaahmed.npl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hamza Ahmed on 23-Jul-17.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> scoreList;
    private User mUser;
    private Context context;

    public UserAdapter(List<User> mscoreList) {
        scoreList = mscoreList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_of_users, viewGroup, false);
        context =view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        Log.d("User1: ",""+scoreList.get(i));
        User user = scoreList.get(i);
        //Log.d("User: ",""+User.getId());
        viewHolder.bindDeals(user);
    }


    @Override
    public int getItemCount() {
        return scoreList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView Users;
        public ViewHolder(View view) {
            super(view);
            Users =(TextView)view.findViewById(R.id.users);
        }
        public void bindDeals(User user){
            mUser=user;
            Users.setText(mUser.getName());
        }
    }

}
