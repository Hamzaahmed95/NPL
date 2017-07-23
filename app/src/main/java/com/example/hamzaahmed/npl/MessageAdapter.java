package com.example.hamzaahmed.npl;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.auth.api.Auth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hamza Ahmed on 14-Jul-17.
 */

public class MessageAdapter extends ArrayAdapter<FriendlyMessage> {

    String Name;
    private LinearLayout l1;
    List<FriendlyMessage> f2;
    private TextView date1;
    String side;
    private FirebaseAuth mFirebaseAuth;
    FirebaseUser user;
    private  TextView messageTextView;
    private FirebaseDatabase mFirebaseDatabase;
    LinearLayout.LayoutParams params;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private Query mHouseDatabaseReference2;
    ArrayList al = new ArrayList();
    public MessageAdapter(Context context, int resource, List<FriendlyMessage> objects,String name,List<FriendlyMessage> f1) {
        super(context, resource, objects);
        Name=name;
        f2=f1;

    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_message, parent, false);
        }
        mFirebaseAuth = FirebaseAuth.getInstance();
        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
       messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        final ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.progress);
        date1=(TextView)convertView.findViewById(R.id.Date1);
        TextView authorTextView = (TextView) convertView.findViewById(R.id.nameTextView);
        l1=(LinearLayout)convertView.findViewById(R.id.rightLayout);
        Log.d("position",""+getItem(position));
        FriendlyMessage message = getItem(position);
        FriendlyMessage m = new FriendlyMessage();

        int count=0;
        Log.d("pos1",""+position);
        String name1="";

        /*for(int i=0;i<f2.size();i++){
            Log.d("adapter",""+f2.get(i).getName());

            if(f2.get(i).getName().equals(Name)){
                count++;
                Log.d("adapt2",""+Name+""+f2.get(i).getName());

                name1=f2.get(i).getName();
                Log.d("count = ",""+count);
            }

        }*/

        /*Log.d("name1",name1);
        if()){
            Log.d("names",Name+" "+name1);

        }
        else{


        }*/
        params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);

        user = mFirebaseAuth.getCurrentUser();
        Log.d("class",mFirebaseAuth.getClass().getName());
       /* mHouseDatabaseReference2 =mFirebaseDatabase.getReference().child("messages").orderByChild("name");
        mHouseDatabaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println(issue.getValue());
                        if(issue.child("name").getValue().equals(user.getDisplayName())){
  /*                          messageTextView.setTextColor((Color.parseColor("#800000")));



*/


                /*        }else {
                            params.weight=1.0f;
                            params.gravity=Gravity.END;
                            l1.setLayoutParams(params);
                        }


                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/


            boolean isPhoto = message.getPhotoUrl() != null;
        if (isPhoto) {

            messageTextView.setVisibility(View.GONE);
            if(message.getDate1()!=null)
            date1.setText(message.getDate1());
            photoImageView.setVisibility(View.VISIBLE);
            Glide.with(photoImageView.getContext())
                    .load(message.getPhotoUrl())
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(photoImageView);
        } else {
            messageTextView.setVisibility(View.VISIBLE);
            photoImageView.setVisibility(View.GONE);
            if(message.getDate1()!=null)
            date1.setText(message.getDate1());
            progressBar.setVisibility(View.GONE);
            messageTextView.setText(message.getText());
        }

       // System.out.println(message.getSide());
/*        if(!message.getSide().equals(null))
        if(message.getSide().equals("LEFT")) {
            params.weight = 1.0f;
            params.gravity = Gravity.LEFT;
            l1.setLayoutParams(params);
        }
        else{
            params.weight = 1.0f;
            params.gravity = Gravity.RIGHT;
            l1.setLayoutParams(params);
            //System.out.println("right side");

        }
  */      authorTextView.setText(message.getName());




        return convertView;
    }

}
