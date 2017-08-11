package myApp.hamza.npl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Hamza Ahmed on 23-Jul-17.
 */

public class Users extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter adapter;

    @Override
    protected void onCreate( final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.users);

        Bundle b=this.getIntent().getExtras();
        int count = b.getInt("count");
        String[] array=b.getStringArray("users");
        array = new HashSet<String>(Arrays.asList(array)).toArray(new String[0]);
        int count1 =array.length;
        for(int j=0;j<count1;j++){
            System.out.println("Users:"+j+" "+array[j]);
        }
        recyclerView = (RecyclerView)findViewById(R.id.listOfUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        updateUI(array,count1);

    }

    public void updateUI(String [] array,int count1){
        UserLab userLab = new UserLab(this,array,count1);
        List<User> user= userLab.getmUser();
        adapter = new UserAdapter(user);
        recyclerView.setAdapter(adapter);

    }
}
